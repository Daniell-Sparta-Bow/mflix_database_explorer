package org.sparta.tech259.finalproject.controller.rest;

import org.sparta.tech259.finalproject.model.entities.Users;
import org.sparta.tech259.finalproject.model.exception.users.UsersNotCreatedException;
import org.sparta.tech259.finalproject.model.exception.users.UsersNotDeletedException;
import org.sparta.tech259.finalproject.model.exception.users.UsersNotFoundException;
import org.sparta.tech259.finalproject.model.exception.users.UsersNotUpdatedException;
import org.sparta.tech259.finalproject.model.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsersRestController {
    private final UsersRepository usersRepository;
    
    @Autowired
    public UsersRestController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    //Read
    @GetMapping("/users")
    public List<Users> getAllUsers() throws UsersNotFoundException {
        List<Users> usersList = usersRepository.findAll();
        if(usersList.isEmpty()){
            throw new UsersNotFoundException("All");
        } else{
            return usersList;
        }
    }

    @GetMapping("/user/{id}")
    public Users getUserById(@PathVariable String id) throws UsersNotFoundException {
        Optional<Users> optionalUser = usersRepository.findById(id);
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }
        else{
            throw new UsersNotFoundException(id);
        }
    }

    @GetMapping("/users/{name}")
    public List<Users> getUsersByName(@PathVariable String name) {
        return usersRepository.findUsersByName(name);
    }

    //Create
    @PostMapping("/user")
    public Users createUser(@RequestBody Users user) throws UsersNotCreatedException {
        try{
            return usersRepository.save(user);
        }
        catch (Exception e){
            throw new UsersNotCreatedException(user.getName());
        }
    }

    //Update
    @PutMapping("/user/{id}")
    public Users updateUser(@PathVariable String id, @RequestBody Users user) throws UsersNotUpdatedException {
        try {
            Optional<Users> usersOptional = usersRepository.findById(id);
            if(usersOptional.isPresent()){
                Users userToUpdate = usersOptional.get();
                userToUpdate.setName(user.getName());
                userToUpdate.setPassword(user.getPassword());
                userToUpdate.setEmail(user.getEmail());
                return usersRepository.save(userToUpdate);
            }
            else {
                return null;
            }
        }
        catch (Exception e){
            throw new UsersNotUpdatedException(user.getName());
        }
    }

    //delete
    @DeleteMapping("/user/delete/{id}")
    public void deleteUser(@PathVariable String id) throws UsersNotDeletedException, UsersNotFoundException {
        Optional<Users> usersOptional = usersRepository.findById(id);
        if(usersOptional.isPresent()){
            try {
                usersRepository.delete(usersOptional.get());
            }
            catch (Exception e){
                throw new UsersNotDeletedException(usersOptional.get().getName());
            }
        }
        else {
            throw new UsersNotFoundException(id);
        }
    }



}
