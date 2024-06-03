package org.sparta.tech259.finalproject.controller.web;

import jakarta.servlet.http.HttpServletRequest;
import org.sparta.tech259.finalproject.model.entities.movies.Movie;
import org.sparta.tech259.finalproject.service.CommentService;
import org.sparta.tech259.finalproject.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.List;
import java.util.Optional;

@Controller
public class MovieWebController {

    private final MovieService movieService;
    private final CommentService commentService;

    @Autowired
    public MovieWebController(MovieService movieService, CommentService commentService) {
        this.movieService = movieService;
        this.commentService = commentService;
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
            model.addAttribute("movie", movie.get());
            model.addAttribute("comments", commentService.getCommentsByMovieId(movie.get().getId()));
            return "movie";
        }
    }

    //create
    @GetMapping("/movie/create")
    public String createMovie(Model model)
    {
        Movie movie = new Movie();
        movie.setDirectors(List.of(""));
        movie.setCast(List.of(""));
        model.addAttribute("movie", movie);
        return "movie-create";
    }

    @PostMapping(value = "/movie/create/save", params = {"addField"})
    public String addField(@ModelAttribute("movie") Movie movie, Model model, HttpServletRequest request) {
        String field = request.getParameter("addField");
        if ("director".equals(field)) {
            movie.getDirectors().add("");
        } else if ("cast".equals(field)) {
            movie.getCast().add("");
        }
        model.addAttribute("movie", movie);
        return "movie-create";
    }

    //save created movie
    @PostMapping("/movie/create/save")
    public String creatingMovie(@ModelAttribute("movie") Movie movie)
    {
        movieService.createMovie(movie);
        return "redirect:/movies";
    }


    //edit movie info
    @GetMapping("/movie/edit/{id}")
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
    @PostMapping("/movie/edit/save/{id}")
    public String updateMovie(
            @ModelAttribute("movie") Movie movie,
            @PathVariable String id) {
//        movieService.updateMovie(id, movie);
        return "redirect:/movies";
    }

    // delete movie by id
    @GetMapping("/movie/delete/{id}")
    public String deleteMovieById(@PathVariable String id) {
        movieService.deleteMovie(id);

        return "redirect:/movies";
    }

}
