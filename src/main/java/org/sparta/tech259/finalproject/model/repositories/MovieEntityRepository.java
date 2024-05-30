package org.sparta.tech259.finalproject.model.repositories;

import org.bson.types.ObjectId;
import org.sparta.tech259.finalproject.model.entities.movies.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieEntityRepository extends MongoRepository<Movie, ObjectId> {
    Optional<Movie> findByTitle(String title);

    List<Movie> findByYear(String year);

    @Query("{ 'genres' : ?0 }")
    List<Movie> findByGenresContaining(String genre);

    Optional<Movie> findBy_id(String movieId);

    List<Movie> findByLanguages(List<String> languages);

    List<Movie> findByRated(String rated);

    List<Movie> findByCountries(List<String> countries);

    Optional<Movie> findById(String movieId);
}
