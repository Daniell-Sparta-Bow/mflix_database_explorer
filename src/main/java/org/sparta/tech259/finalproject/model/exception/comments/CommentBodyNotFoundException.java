package org.sparta.tech259.finalproject.model.exception.comments;

public class CommentBodyNotFoundException extends Exception {
     public CommentBodyNotFoundException() {
         super("The comment body is missing from the request. Please include a comment and try again.");
     }
}
