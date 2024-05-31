package org.sparta.tech259.finalproject.controller.rest;


import org.sparta.tech259.finalproject.model.entities.movies.Movie;

import org.sparta.tech259.finalproject.model.exception.movies.MovieIdNotFoundException;
import org.sparta.tech259.finalproject.model.exception.movies.MoviesNotFoundException;
import org.sparta.tech259.finalproject.model.exception.movies.RequestBodyNotFoundException;
import org.sparta.tech259.finalproject.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MovieRestController {
    private final MovieService movieService;

    @Autowired
    public MovieRestController(MovieService movieService){
        this.movieService= movieService;
    }

    //read all
    @GetMapping("/movies")
    public List<Movie> getAllMovies() {

        List<Movie> movies = movieService.getAllMovies();
        if(movies.isEmpty()){
            throw new MoviesNotFoundException();
        }
        return movies;

    }

    //read singular
    @GetMapping("/movie/{id}")
    public Movie getMoviesById(@PathVariable("id") String id){

        if(id.isEmpty() || id == null){
            throw new MovieIdNotFoundException(id);
        }
     Optional<Movie> movie = movieService.getMovieById(id);
        return movie.orElse(null);
    }

    //delete
    @DeleteMapping("/movie/{id}")
    public void deleteMovie(@PathVariable String id){

        Optional<Movie> findMovie = movieService.getMovieById(id);
         if(findMovie.isEmpty()){
             throw new MovieIdNotFoundException(id);
         }
        movieService.deleteMovie(id);
    }

    //add
    @PostMapping("/movie")
    public Movie addMovie(@RequestBody Movie movie){

          if(movie == null) {
              throw new RequestBodyNotFoundException();
          }
        return movieService.createMovie(movie);
    }

    //update
    @PutMapping("/movie/{id}")
    public Movie updateMovie(@RequestBody Movie movie, @PathVariable String id){
        Optional<Movie> movieToUpdate = movieService.getMovieById(id);
        if (movieToUpdate.isPresent()) {
            return movieService.updateMovie(id, movie);
        } else {

            throw new RequestBodyNotFoundException();

        }
    }
}
