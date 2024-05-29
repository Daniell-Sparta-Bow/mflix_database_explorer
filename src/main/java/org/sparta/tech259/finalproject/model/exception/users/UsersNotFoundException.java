package org.sparta.tech259.finalproject.model.exception.users;

public class UsersNotFoundException extends Exception{
    public UsersNotFoundException(String name){
        super("User: " + name +" could not be found");
    }
}
