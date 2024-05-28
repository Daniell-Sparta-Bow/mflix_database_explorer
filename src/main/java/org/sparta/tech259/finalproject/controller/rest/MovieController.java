package org.sparta.tech259.finalproject.controller.rest;


import org.bson.types.ObjectId;
import org.sparta.tech259.finalproject.entities.Movie;
import org.sparta.tech259.finalproject.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService){
        this.movieService= movieService;
    }

    @GetMapping("/movies")
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();

    }

    @GetMapping("/movie/language")
    public List<Movie> getAllM(@RequestParam(name="movie") String movie) {
        List<Movie> movie = movieService.getMovieByLanguage(movie);
        if(movie.isEmpty()){

        }

    }

    @GetMapping("/movies/movie/{id}")
    public Movie getMoviesByIs(@PathVariable("id")ObjectId id){
        return movieService.getMovieById(id).get();
    }


}
