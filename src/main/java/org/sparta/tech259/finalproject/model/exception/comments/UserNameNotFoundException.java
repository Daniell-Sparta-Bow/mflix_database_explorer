package org.sparta.tech259.finalproject.model.exception.comments;

public class UserNameNotFoundException extends Exception{
    public UserNameNotFoundException(String userName) {
        super("No comments found for user " + userName + ". Please verify the username and try again." );
    }
}
