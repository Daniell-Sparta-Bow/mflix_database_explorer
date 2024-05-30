package org.sparta.tech259.finalproject.model.repositories;

import org.bson.types.ObjectId;
import org.sparta.tech259.finalproject.model.entities.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends MongoRepository<Users, String> {
    Optional<Users> findBy_id(String _id);
    List<Users>findUsersByName(String name);
    Optional<Users>  findUserByEmail(String email);
}
