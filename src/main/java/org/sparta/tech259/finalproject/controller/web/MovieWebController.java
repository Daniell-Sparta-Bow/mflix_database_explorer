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
        Optional<Movie> movie = movieService.getMovieById(id);
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

    //edit movie info
    @GetMapping("movie/edit/{id}")
    public String editMovie(@PathVariable String id, Model model)
    {
        Optional<Movie> movie = movieService.getMovieById(id);
        if(movie.isPresent())
        {
            Movie movieToEdit = movie.get();
            model.addAttribute("movie", movieToEdit);
            return "movie-edit";
        }
        else{
            throw new RuntimeException("Movie not found");
        }
    }

    //save edited movie info
    @PostMapping("movie/edit/save/{id}")
    public String updateMovie(@ModelAttribute("movie") Movie movie, @PathVariable String id) {
        movieService.updateMovie(id, movie);
        return "redirect:/web/movies";
    }

    // delete movie by id
    @GetMapping("/movie/delete/{id}")
    public String deleteMovieById(@PathVariable String id) {
        movieService.deleteMovie(id);
        return "redirect:/web/movies";
    }

}
