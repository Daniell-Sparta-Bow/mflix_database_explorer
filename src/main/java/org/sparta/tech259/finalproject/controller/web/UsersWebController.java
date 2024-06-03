package org.sparta.tech259.finalproject.controller.web;

import org.sparta.tech259.finalproject.model.entities.Users;
import org.sparta.tech259.finalproject.model.exception.users.UsersNotCreatedException;
import org.sparta.tech259.finalproject.model.exception.users.UsersNotFoundException;
import org.sparta.tech259.finalproject.model.exception.users.UsersNotUpdatedException;
import org.sparta.tech259.finalproject.model.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Controller
public class UsersWebController {
    private final UsersRepository usersRepository;

    @Autowired
    public UsersWebController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    //get user
    @GetMapping("/user/{id}")
    public String getUser(@PathVariable String id, Model model) throws UsersNotFoundException {
        Optional<Users> usersOptional = usersRepository.findBy_id(id);
        if(usersOptional.isPresent()) {
            Users users = usersOptional.get();
            model.addAttribute("user", users);
            return "user";
        }
        else {
            throw new UsersNotFoundException(id);
        }
    }

    //get all users
    @GetMapping("/users")
    public String getUsers(Model model) throws UsersNotFoundException {
        List<Users> usersList = usersRepository.findAll();
        if(usersList.isEmpty()) {
            throw new UsersNotFoundException("All");
        }
        else {
            model.addAttribute("users", usersRepository.findAll());
            return "users";
        }
    }

    //create user
    @GetMapping("/user/create")
    public String createUser(Model model) {
        Users newUser = new Users(null,null,null);
        model.addAttribute("user", newUser);
        return "user-create";
    }

    //save created user
    @PostMapping("/user/create/save")
    public String creatingUser(@ModelAttribute("user") Users user) {
        usersRepository.save(user);
        return "redirect:/users";
    }

    //edit user info
    @GetMapping("user/edit/{id}")
    public String editUser(@PathVariable String id, Model model) throws UsersNotFoundException {
        Optional<Users> userToUpdate = usersRepository.findBy_id(id);
        if(userToUpdate.isPresent()) {
            Users userToEdit = userToUpdate.get();
            model.addAttribute("user", userToEdit);
            return "user-edit";
        }
        else {
            throw new UsersNotFoundException(id);
        }
    }

    //save edited user info
    @PostMapping("/user/edit/save/{id}")
    public String updateUser(@ModelAttribute("user") Users newUser, @PathVariable String id) throws UsersNotUpdatedException {
        Optional<Users> optionalUser = usersRepository.findBy_id(id);
        if (optionalUser.isPresent()) {
            Users oldUser = optionalUser.get();
            oldUser.setName(newUser.getName());
            oldUser.setEmail(newUser.getEmail());
            oldUser.setPassword(newUser.getPassword());
            usersRepository.save(oldUser);
            return "redirect:/users";
        } else {
            throw new UsersNotUpdatedException(newUser.getName());
        }
    }


    //delete
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable String id) throws UsersNotFoundException {
        Optional<Users> optionalUser = usersRepository.findBy_id(id);
        if(optionalUser.isPresent()) {
            Users userToDelete = optionalUser.get();
            usersRepository.delete(userToDelete);
            return "redirect:/users";
        } else {
            throw new UsersNotFoundException(id);
        }
    }
}