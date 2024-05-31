package org.sparta.tech259.finalproject.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.sparta.tech259.finalproject.model.entities.Users;
import org.sparta.tech259.finalproject.model.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UsersControllerTests {
    WebTestClient webTestClient;

    @MockBean
    private UsersRepository usersRepository;

    private List<Users> expectedUsers;

    @BeforeEach
    public void setup(){
        webTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:8080").build();
        expectedUsers = Arrays.asList(
                new Users("murad", "murad@gmail.com", "password"),
                new Users("Patrick", "patrick@gmail.com", "passwrd.com")
        );

        when(usersRepository.findAll()).thenReturn(expectedUsers);

    }


    @Test
    public void testGetAllUsers() {
        webTestClient.get().uri("/users")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Users.class)
                .isEqualTo(expectedUsers);
    }

    @Test
    public void testGetUserById() {
        Users user = expectedUsers.get(0);
        when(usersRepository.findById("1")).thenReturn(Optional.of(user));

        webTestClient.get().uri("/user/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Users.class)
                .isEqualTo(user);
    }


    @Test
    public void testGetUsersByName() {
        String name = "murad";
        List<Users> usersByName = expectedUsers.stream()
                .filter(user -> user.getName().equals(name))
                .collect(Collectors.toList());
        when(usersRepository.findUsersByName(name)).thenReturn(usersByName);

        webTestClient.get().uri("/users/{name}", name)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Users.class)
                .isEqualTo(usersByName);
    }


    @Test
    public void testCreateUser() {
        Users newUser = new Users("NewUser", "newuser@gmail.com", "newpassword");
        when(usersRepository.save(newUser)).thenReturn(newUser);

        Users responseBody = webTestClient.post().uri("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(newUser)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Users.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(responseBody);
        assertEquals(newUser, responseBody);
    }

//    @Test
//    public void testUpdateUser() {
//        Users existingUser = expectedUsers.get(0);
//        Users updatedUser = new Users("UpdatedName", "updatedemail@gmail.com", "updatedpassword");
//
//        when(usersRepository.findById(existingUser.get_id())).thenReturn(Optional.of(existingUser));
//        when(usersRepository.save(updatedUser)).thenReturn(updatedUser);
//        usersRepository.save(existingUser);
//
//        Users responseBody = webTestClient.put().uri("/user/{id}", existingUser.get_id())
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(updatedUser)
//                .exchange()
//                .expectStatus().isOk()
//                .expectBody(Users.class)
//                .returnResult()
//                .getResponseBody();
//
//        assertNotNull(responseBody);
//        assertEquals(updatedUser, responseBody);
//    }

}
