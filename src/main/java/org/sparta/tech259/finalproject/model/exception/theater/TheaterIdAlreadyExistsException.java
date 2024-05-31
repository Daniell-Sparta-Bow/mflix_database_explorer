package org.sparta.tech259.finalproject.model.exception.theater;

public class TheaterIdAlreadyExistsException extends RuntimeException {
    public TheaterIdAlreadyExistsException(int theaterId){
        super("Theater with Id " + theaterId + " already exists.");
    }
}
