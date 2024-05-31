package org.sparta.tech259.finalproject.controller;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import org.sparta.tech259.finalproject.controller.rest.CommentsRestController;
import org.sparta.tech259.finalproject.model.entities.Comment;
import org.sparta.tech259.finalproject.service.CommentService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

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
        when(commentService.getCommentsByUserName("user1")).thenReturn(List.of(expectedComments.get(0)));
        when(commentService.getCommentsByMovieId("60c72b2f9b1d4f5d4c8e2f4b")).thenReturn(List.of(expectedComments.get(0)));

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

    @Test
    @DisplayName("Check that get comments by user name returns comments authored by that user")
    void checkThatGetCommentsByUserNameReturnsCommentsAuthoredByThatUser() {
        List<Comment> expectedUserComments = List.of(expectedComments.get(0));

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("api/movie/comments/userName")
                        .queryParam("username","user1")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Comment.class)
                .value(actualComments ->{
                    Assertions.assertEquals(expectedUserComments.size(), actualComments.size());
                    Assertions.assertEquals(expectedUserComments.get(0).getId(), actualComments.get(0).getId());
                } );
    }

    @Test
    @DisplayName("Check that get comments by movie id returns comments for that movie")
    void checkThatGetCommentsByMovieIdReturnsComments() {
        List<Comment> expectedMovieComments = List.of(expectedComments.get(0));
        webTestClient.get()
                .uri("/api/movie/comments/60c72b2f9b1d4f5d4c8e2f4b")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Comment.class)
                .value(actualComments -> {
                    Assertions.assertEquals(expectedMovieComments.size(), actualComments.size());
                    Assertions.assertEquals(expectedMovieComments.get(0).getId(), actualComments.get(0).getId());
                });
    }

    @Test
    @DisplayName("Check that adding a comment returns the created comment")
    void checkThatAddingACommentReturnsTheCreatedComment() {
        Comment newComment = new Comment("4", LocalDateTime.now(), "user4@example.com", new ObjectId("60c72b2f9b1d4f5d4c8e2f4e"), "I hate this movie!", "user4");

        webTestClient.post()
                .uri("/api/movie/comments")
                .bodyValue(newComment)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Comment.class)
                .value(returnedComment -> {
                    Assertions.assertEquals(newComment.getId(), returnedComment.getId());
                    Assertions.assertEquals(newComment.getEmail(), returnedComment.getEmail());
                    Assertions.assertEquals(newComment.getText(), returnedComment.getText());
                    Assertions.assertEquals(newComment.getName(), returnedComment.getName());
                });
    }

    @Test
    @DisplayName("Check that deleting a comment returns a success message")
    void checkThatDeletingACommentReturnsSuccessMessage() {
        String commentId = "1";
        when(commentService.getCommentByCommentId(commentId)).thenReturn(Optional.of(expectedComments.get(0)));

        webTestClient.delete()
                .uri("/api/movie/comment/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(responseMessage -> {
                    Assertions.assertTrue(responseMessage.contains("Comment with id " + commentId + " was deleted"));
                });
    }

}