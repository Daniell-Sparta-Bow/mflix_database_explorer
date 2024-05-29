package org.sparta.tech259.finalproject.model.repositories;

import org.bson.types.ObjectId;
import org.sparta.tech259.finalproject.model.entities.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UsersRepository extends MongoRepository<Users, String> {
    public Users findBy_id(String _id);
    public List<Users>findUsersByName(String name);
    public Users findUserByEmail(String email);
}
