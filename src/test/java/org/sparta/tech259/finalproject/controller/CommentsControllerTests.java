package org.sparta.tech259.finalproject.controller;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import org.sparta.tech259.finalproject.controller.rest.CommentsRestController;
import org.sparta.tech259.finalproject.model.entities.Comment;
import org.sparta.tech259.finalproject.model.exceptions.CommentsNotFoundException;
import org.sparta.tech259.finalproject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.ErrorResponse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)

public class CommentsControllerTests {

    WebTestClient webTestClient;

    private CommentsRestController commentsRestController;

    @MockBean
    private CommentService commentService;

    private List<Comment> expectedComments;
    @BeforeEach
    public void setup(){
        webTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:8080").build();
        expectedComments = Arrays.asList(
                new Comment("1", LocalDateTime.now(), "user1@example.com", new ObjectId("60c72b2f9b1d4f5d4c8e2f4b"), "Great movie!", "user1"),
                new Comment("2", LocalDateTime.now(), "user2@example.com", new ObjectId("60c72b2f9b1d4f5d4c8e2f4c"), "I enjoyed it.", "user2"),
                new Comment("3", LocalDateTime.now(), "user3@example.com", new ObjectId("60c72b2f9b1d4f5d4c8e2f4d"), "Could have been better.", "user3")
        );
        when(commentService.getAllComments()).thenReturn(expectedComments);
        when(commentService.getCommentByCommentId("1")).thenReturn(Optional.ofNullable(expectedComments.get(0)));

    }

    @Test
    @DisplayName("Check that the status code is 2xx when getting all comments ")
    public void checkThatTheStatusCodeIs2XxWhenGettingAllComments() {
        webTestClient.get()
                .uri("api/movie/comments").
                 exchange().
                 expectStatus().is2xxSuccessful();

    }

    @Test
    @DisplayName("Check it returns a response body for all comments")
    public void checkItReturnsAResponseBodyForAllComments() {
        List<Comment> actualComments = webTestClient.get()
                .uri("api/movie/comments")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Comment.class)
                .returnResult().getResponseBody();

       Assertions.assertEquals(expectedComments.size(), actualComments.size());
    }

    @Test
    @DisplayName("Check that get comments by id returns a specific comment")
    void checkThatGetCommentsByIdReturnsAComment() {
        Comment expectedComment = expectedComments.get(0);
               webTestClient.get()
               .uri("api/movie/comments/comment-id/1")
               .exchange()
               .expectStatus().isOk()
               .expectBody(Comment.class)
               .value(actualComment ->{
                  Assertions.assertEquals(actualComment.getId(), expectedComment.getId());
               } );
    }





}
