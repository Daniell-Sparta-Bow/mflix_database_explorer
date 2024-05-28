package org.sparta.tech259.finalproject.repositories;

import org.bson.types.ObjectId;
import org.sparta.tech259.finalproject.entities.Movie;
import org.springframework.data.mongodb.core.MongoAction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieEntityRepository extends MongoRepository<Movie, ObjectId> {
    Optional<Movie> findByTitle(String title);
    List<Movie> findByYear(int year);
    List<Movie> findByGenresContaining(String genre);

    Optional<Movie> findById(ObjectId movieId);
    

    List<Movie> findByLanguages(String language);

    List<Movie> findByRated(int rating);

    List<Movie> findByCountries(String country);
}
