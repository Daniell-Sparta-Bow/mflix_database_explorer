package org.sparta.tech259.finalproject.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static java.util.Optional.of;
import static org.mockito.Mockito.*;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sparta.tech259.finalproject.model.entities.movies.Movie;
import org.sparta.tech259.finalproject.model.repositories.MovieEntityRepository;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class MoviesServiceTests {
    @Mock
    private MovieEntityRepository movieEntityRepository;

    @InjectMocks
    public MovieService movieService;

    @BeforeEach
    public void setUp() throws Exception {
//        lenient().when(movieEntityRepository.findBy_id("573a1390f29313caabcd4eaf")).thenReturn(Optional.empty());
//        when(this.movieEntityRepository.save(any())).thenReturn(new Movie());
//        //doReturn("Movie Deleted").when(this.movieEntityRepository).delete(any());
//
    }



    @Test
    @DisplayName("expect false when movie Id does not exist")
    void expectFalseWhenMovieIdDoesNotExist() {
        when(movieEntityRepository.findBy_id("573xyz90f29313caabcd4eaf")).thenReturn(Optional.empty());

        boolean result = movieService.deleteMovie("573xyz90f29313caabcd4eaf");

        Assertions.assertFalse(result);
        verify(movieEntityRepository,times(1)).findBy_id("573xyz90f29313caabcd4eaf");



    }

    @Test
    @DisplayName("Verify movieEntityRepository's save method is called when MovieService calls createMovie")
    public void verifyMovieEntityRepositorySSaveMethodIsCalledWhenMovieServiceCallsCreateMovie() {
        movieService.createMovie(any());
        verify(movieEntityRepository, times(1)).save(any());
                
    }

    @Test
    @DisplayName("Verify movieService calls movieRepository findBy_id method")
    public void verifyMovieServiceCallsMovieRepositoryFindByIdMethod() {

        movieService.getMovieById(any());
        verify(movieEntityRepository, times(1)).findBy_id(any());
                
    }

    @Test
    @DisplayName("Verify movieService calls movieRepository's findAllMovies method")
    public void verifyMovieServiceCallsMovieRepositorySFindAllMoviesMethod() {
        movieService.getAllMovies();
        verify(movieEntityRepository, times(1)).findAll();

    }

    @Test
    @DisplayName("Verify movieService calls movieRepository's delete method")
    public void verifyMovieServiceCallsMovieRepositorySDeleteMethod() {
        when(movieEntityRepository.findBy_id(any())).thenReturn(of(new Movie()));
        movieService.deleteMovie(any());
        verify(movieEntityRepository, times(1)).delete(any());

    }

    @Test
    @DisplayName("verify movieService calls movieRepository's findByGenresContaining method")
    public void verifyMovieServiceCallsMovieRepositorySFindByGenresContainingMethod() {
        when(movieEntityRepository.findByGenresContaining(any())).thenReturn(List.of(new Movie()));
        movieService.getMoviesByGenres(any());
        verify(movieEntityRepository, times(1)).findByGenresContaining(any());
    }

    @Test
    @DisplayName("verify movieService calls movieRepository's save method when updating")
    public void verifyMovieServiceCallsMovieRepositorySSaveMethodWhenUpdating() {
        when(movieEntityRepository.findBy_id(any())).thenReturn(of(new Movie()));
        movieService.updateMovie(anyString(), any(Movie.class));
        verify(movieEntityRepository, times(1)).findBy_id(any());
        verify(movieEntityRepository, times(1)).save(any());
    }
     @Test
     @DisplayName("verify movieService calls movieRepository's findByLanguages method")
     public void verifyMovieServiceCallsMovieRepositorySFindByLanguagesMethod() {
        when(movieEntityRepository.findByLanguages(any())).thenReturn(List.of(new Movie()));
        movieService.getMovieByLanguage(any());
        verify(movieEntityRepository, times(1)).findByLanguages(any());
     }

     @Test
     @DisplayName("verify movieService calls movieRepository's findByCountries method")
     public void verifyMovieServiceCallsMovieRepositorySFindByCountriesMethod() {
        when(movieEntityRepository.findByCountries(any())).thenReturn(List.of(new Movie()));
        movieService.getMovieByCountry(any());
        verify(movieEntityRepository, times(1)).findByCountries(any());
     }

     

}
