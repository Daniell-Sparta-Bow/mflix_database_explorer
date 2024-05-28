package org.sparta.tech259.finalproject.model.repositories;

import org.sparta.tech259.finalproject.model.entities.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface CommentRepository extends MongoRepository<Comment, String> {

  Optional<Comment> findBy_id(String id);
}
