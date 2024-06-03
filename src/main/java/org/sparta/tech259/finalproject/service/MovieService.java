package org.sparta.tech259.finalproject.service;

import org.bson.types.ObjectId;
import org.sparta.tech259.finalproject.model.entities.movies.Movie;
import org.sparta.tech259.finalproject.model.repositories.MovieEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {


    @Autowired
    private final MovieEntityRepository movieEntityRepository;

    public MovieService(MovieEntityRepository movieEntityRepository) {
        this.movieEntityRepository = movieEntityRepository;
    }

    public List<Movie> getAllMovies(){
//        return movieEntityRepository.findAll();
        Pageable firstTen = PageRequest.of(0, 10);
        Page<Movie> moviePage = movieEntityRepository.findAll(firstTen);
        return moviePage.getContent();
    }


    public Optional<Movie> getMovieById(String movieId){
        return movieEntityRepository.findBy_id(movieId);
    }


    public Optional<Movie> getMovieByTitle(String title){
        return movieEntityRepository.findByTitle(title);
    }

    public List<Movie> getMoviesByGenres(String genre) {
        return movieEntityRepository.findByGenresContaining(genre);
    }


    public List<Movie> getMoviesByYear(String year){
        return movieEntityRepository.findByYear(year);
    }

    public List<Movie> getMovieByLanguage(String language){
        return movieEntityRepository.findByLanguages(Collections.singletonList(language));

    }

    public List<Movie> getMovieByCountry(String country){
        return movieEntityRepository.findByCountries(Collections.singletonList(country));
    }
    

    public Movie createMovie(Movie movie){
        return movieEntityRepository.save(movie);
    }

    public boolean deleteMovie(String id){
        Optional<Movie> movieOptional = movieEntityRepository.findBy_id(id);
        if (movieOptional.isPresent()){
        movieEntityRepository.delete(movieOptional.get());
        return true;
        }else {
            return false;
        }
    }

    public Movie updateMovie(String id,  Movie updatedMovie){
        Optional<Movie> optionalMovie = movieEntityRepository.findBy_id(id);
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
