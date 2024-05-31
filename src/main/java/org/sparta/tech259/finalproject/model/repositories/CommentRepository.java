package org.sparta.tech259.finalproject.model.repositories;

import org.bson.types.ObjectId;
import org.sparta.tech259.finalproject.model.entities.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;
import java.util.List;
public interface CommentRepository extends MongoRepository<Comment, String> {

  Optional<Comment> findBy_id(String id);
  List<Comment> findByName(String name);

  default List<Comment> findByMovieId(String movieId) {
    ObjectId objectId = new ObjectId(movieId);
    return findByMovieId(objectId);
  }


  List<Comment> findByMovieId(ObjectId movieId);

  };

