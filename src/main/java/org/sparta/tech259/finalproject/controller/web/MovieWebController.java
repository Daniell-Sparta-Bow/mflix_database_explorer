package org.sparta.tech259.finalproject.controller.web;

import org.sparta.tech259.finalproject.model.entities.movies.Movie;
import org.sparta.tech259.finalproject.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/web")
public class MovieWebController {

    private final MovieService movieService;

    @Autowired
    public MovieWebController(MovieService movieService) {
        this.movieService = movieService;
    }

    // get all movies
    @GetMapping("/movies")
    public String getMovies(Model model) {
        List<Movie> movieList = movieService.getAllMovies();
        if (movieList.isEmpty()) {
            return "redirect:/error";
        } else {
            model.addAttribute("movies", movieList);
            return "movies";
        }
    }

    //get movie by id
    @GetMapping("/movie/{id}")
    public String getMovieById(@PathVariable String id, Model model) {
        Optional movie = movieService.getMovieById(id);
        if (movie.isEmpty()) {
            return "redirect:/error";
        } else {
            model.addAttribute("movie", movie);
            return "movie";
        }
    }

    //create
    @GetMapping("/movie/create")
    public String createMovie(Model model)
    {
        Movie movie = new Movie();
        model.addAttribute("movie", movie);
        return "movie-create";
    }

    //save created movie
    @PostMapping("/movie/create/save")
    public String creatingMovie(@ModelAttribute("movie") Movie movie)
    {
        movieService.createMovie(movie);
        return "redirect:/web/movies";
    }




    // delete movie by id
    @GetMapping("/movie/{id}")
    public String deleteMovieById(@PathVariable String _id){
        movieService.deleteMovie(_id);
        return "redirect:/web/movies";
    }

}
