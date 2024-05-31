package org.sparta.tech259.finalproject.model.exception.movies;
//
public class MovieNotFoundException extends RuntimeException{
  public MovieNotFoundException(String movieId) {
    super("No matching movie found for the given ID: " + movieId + ". Please check the ID and try again.");
  }

}
