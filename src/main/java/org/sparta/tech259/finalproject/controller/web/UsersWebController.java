package org.sparta.tech259.finalproject.controller.web;

import org.sparta.tech259.finalproject.model.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;

@Controller
public class UsersWebController {
    private final UsersRepository usersRepository;

    @Autowired
    public UsersWebController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping("/web/user/{id}")
    public String getUser(@PathVariable String id, Model model) {
        model.addAttribute("user", usersRepository.findBy_id(id));
        return "user";
    }

    @GetMapping("/web/users")
    public String getUsers(Model model) {
        model.addAttribute("users", usersRepository.findAll());
        return "users";
    }


}
