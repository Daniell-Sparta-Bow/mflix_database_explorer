package org.sparta.tech259.finalproject.model.exception.theater;

public class TheaterIdDoesNotExistsException extends RuntimeException{
    public TheaterIdDoesNotExistsException(int id){
        super("Theater Id " + id + " does not exists");
    }
}
