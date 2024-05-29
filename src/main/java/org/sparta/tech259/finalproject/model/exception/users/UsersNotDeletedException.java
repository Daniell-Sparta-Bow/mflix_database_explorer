package org.sparta.tech259.finalproject.model.exception.users;

public class UsersNotDeletedException extends Exception{

    public UsersNotDeletedException(String name){
        super("User: " + name +" could not be deleted");
    }
}
