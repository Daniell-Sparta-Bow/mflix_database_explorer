package org.sparta.tech259.finalproject.service;

import org.bson.types.ObjectId;
import org.sparta.tech259.finalproject.entities.Movie;
import org.sparta.tech259.finalproject.repositories.MovieEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {


    private final MovieEntityRepository movieEntityRepository;


    public MovieService(MovieEntityRepository movieEntityRepository) {
        this.movieEntityRepository = movieEntityRepository;
    }

    public List<Movie> getAllMovies(){
        return movieEntityRepository.findAll();
    }


    public Optional<Movie> getMovieById(ObjectId movieId){
        return movieEntityRepository.findById(movieId);
    }


    public Optional<Movie> getMovieByTitle(String title){
        return movieEntityRepository.findByTitle(title);
    }

    public List<Movie> getMoviesByGenre(String genre){
        return movieEntityRepository.findByGenreContaining(genre);
    }

    public List<Movie> getMoviesByYear(int year){
        return movieEntityRepository.findByYear(year);
    }

    public List<Movie> getMovieByLanguage(String language){
        return movieEntityRepository.findByLanguages(Collections.singletonList(language));

    }

    public List<Movie> getMovieByCountry(String country){
        return movieEntityRepository.findByCountries(Collections.singletonList(country));
    }

    public List<Movie> getMoviesByRating(String rating){
        return movieEntityRepository.findByRated(rating);
    }

    public Movie createMovie(Movie movie){
        return movieEntityRepository.save(movie);
    }


    public boolean deleteMovie(String title){
        Optional<Movie> movieOptional= movieEntityRepository.findByTitle(title);
        if (movieOptional.isPresent()){
        movieEntityRepository.delete(movieOptional.get());
        return true;
        }else {
            return false;
        }
    }







    public Movie updateMovie(ObjectId id,  Movie updatedMovie){
        Optional<Movie> optionalMovie = movieEntityRepository.findById(id);
        if(optionalMovie.isPresent()){
            Movie movie = optionalMovie.get();

            movie.setPlot(updatedMovie.getPlot());
            movie.setGenre(updatedMovie.getGenre());
            movie.setRuntime(updatedMovie.getRuntime());
            movie.setCast(updatedMovie.getCast());
            movie.setPoster(updatedMovie.getPoster());
            movie.setTitle(updatedMovie.getTitle());
            movie.setFullplot(updatedMovie.getFullplot());
            movie.setLanguages(updatedMovie.getLanguages());
            movie.setReleased(updatedMovie.getReleased());
            movie.setDirectors(updatedMovie.getDirectors());
            movie.setRated(updatedMovie.getRated());
            movie.setAwards(updatedMovie.getAwards());
            movie.setLastUpdated(updatedMovie.getLastUpdated());
            movie.setYear(updatedMovie.getYear());
            movie.setImdb(updatedMovie.getImdb());
            movie.setCountries(updatedMovie.getCountries());
            movie.setType(updatedMovie.getType());
            movie.setTomatoes(updatedMovie.getTomatoes());
            return movieEntityRepository.save(movie);
        }else{
            return null;
        }
    }
}
