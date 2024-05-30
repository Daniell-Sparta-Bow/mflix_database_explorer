package org.sparta.tech259.finalproject.service;

import org.junit.jupiter.api.Test;
import org.sparta.tech259.finalproject.model.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.sparta.tech259.finalproject.model.entities.Comment;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class CommentsServiceTests {
    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void testCreateComment() {
        Comment comment = new Comment();
        comment.setName("Manish");
        comment.setText("I hate pokemon");
        comment.setDate(LocalDateTime.of(1,1,1,1,1));
        comment.setEmail("Manish@sparta.com");

        List<Comment> savedComment = commentRepository.findByName("Manish");
        assertNotNull(savedComment.get(0));
        assertNotNull(savedComment.getFirst().getName());
        assertNotNull(savedComment.getFirst().getText());
    }

    @Test
    public void testDeleteComment() {
        Comment comment = new Comment();
        comment.setName("Selam");
        comment.setText("I love China");
        comment.setDate(LocalDateTime.of(1,1,1,1,1));
        comment.setEmail("Selam@sparta.com");
        commentRepository.save(comment);

        List<Comment> savedComment = commentRepository.findByName("Selam");
        Comment commentToDelete = savedComment.get(0);
        String selamId = commentToDelete.getId();
        commentService.deleteComment(selamId);
        assertTrue(commentRepository.findBy_id(selamId).isEmpty());
    }

    @Test
    public void testUpdateComment(){
        Comment comment = new Comment();
        comment.setName("Daniell");
        comment.setText("J'adore le SCRUM");
        comment.setDate(LocalDateTime.of(1,1,1,1,1));
        comment.setEmail("Daniell@sparta.com");
        commentRepository.save(comment);

        comment.setText("I love the SCRUM");
        commentService.updateComment(comment.getId(),comment);
        assertTrue(commentRepository.findByName("Daniell").getFirst().getText().equals("I love the SCRUM"));
    }

    @Test
    public void testGetCommentByName(){
        Comment comment = new Comment();
        comment.setName("Ollie webb");
        comment.setText("ello ello");
        comment.setDate(LocalDateTime.of(1,1,1,1,1));
        comment.setEmail("Ollie@sparta.com");
        commentRepository.save(comment);

        assertFalse(commentRepository.findByName("Ollie").isEmpty());
    }



}
