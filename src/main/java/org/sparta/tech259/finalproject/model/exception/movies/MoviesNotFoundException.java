package org.sparta.tech259.finalproject.model.exception.movies;

public class MoviesNotFoundException extends RuntimeException{
    public MoviesNotFoundException(){
        super("There are no movies available at the moment.");
    }
}
