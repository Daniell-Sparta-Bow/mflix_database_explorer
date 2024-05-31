package org.sparta.tech259.finalproject.model.exceptions;

public class CommentIsNullException extends Exception{
public CommentIsNullException(String commentId){
    super("No matching comment found for the given ID: " + commentId + ". Please check the ID and try again.");
}
}
