package org.sparta.tech259.finalproject.model.exception.comments;

public class CommentsNotFoundException extends Exception{
    public CommentsNotFoundException(){

         super("There are no comments available at the moment.");
    }
}
