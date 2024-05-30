package org.sparta.tech259.finalproject.model.exceptions;

public class CommentsNotFoundException extends Exception{
    public CommentsNotFoundException(){

         super("There are no comments available at the moment.");
    }
}
