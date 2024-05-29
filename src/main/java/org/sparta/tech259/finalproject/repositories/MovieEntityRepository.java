package org.sparta.tech259.finalproject.repositories;

import com.mongodb.lang.NonNull;
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
    List<Movie> findByGenreContaining(String genre);

    @NonNull
    Optional<Movie> findById( @NonNull ObjectId movieId);
    

    List<Movie> findByLanguages(List<String> languages);

    List<Movie> findByRated(String rated);

    List<Movie> findByCountries(List<String> countries);
}