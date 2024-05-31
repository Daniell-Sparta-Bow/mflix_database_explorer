package org.sparta.tech259.finalproject.model.exception.comments;

public class CommentIdNotFoundException extends Exception {
 public CommentIdNotFoundException(String commentId) {

     super("Comment with ID " + commentId + " not found. Please check the ID and try again.");
 }
}
