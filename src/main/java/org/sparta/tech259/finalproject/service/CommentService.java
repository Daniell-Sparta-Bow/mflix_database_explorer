package org.sparta.tech259.finalproject.service;

import org.bson.types.ObjectId;
import org.sparta.tech259.finalproject.model.entities.Comment;
import org.sparta.tech259.finalproject.model.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
  private final CommentRepository commentRepository;


  public CommentService(CommentRepository commentRepository) {
    this.commentRepository = commentRepository;
  }

  public Optional<Comment> getCommentByCommentId(String id) {
    Comment comment = commentRepository.findBy_id(id).orElse(null);
    System.out.println("Fetching comment with id: " + id);
    System.out.println("Found comment: " + comment);
    return Optional.of(comment);
  }

  public List<Comment> getAllComments() {
    return commentRepository.findAll();
  }


  public List<Comment> getCommentsByUserName(String userName) {
    List<Comment> comments = commentRepository.findByName(userName);
     return comments;
  }
  public List<Comment> getCommentsByMovieId(String movieId){
    ObjectId movieIdObj = new ObjectId(movieId);
    return commentRepository.findByMovieId(movieIdObj);
  }

  public Comment createComment(Comment comment) {
    comment.setDate(LocalDateTime.now());
    return commentRepository.save(comment);
  }

  public Comment deleteComment(String id) {
      Comment commentToDelete = commentRepository.findById(id).orElse(null);
      commentRepository.deleteById(id);
      return commentToDelete;
  }

  public Comment updateComment(String id, Comment commentToUpdate) {
    Optional<Comment> originalComment = commentRepository.findById(id);
    if(originalComment.isPresent()) {
      Comment updatedComment = originalComment.get();
      //updatedComment.setName(commentToUpdate.getName());
      updatedComment.setDate(LocalDateTime.now());
      updatedComment.setText(commentToUpdate.getText());
      return commentRepository.save(updatedComment);
    }
    return null;
  }

}
