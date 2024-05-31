package org.sparta.tech259.finalproject.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationWebController {

    @GetMapping("/")//homepage
    public String getHomepage() {
        return "home";
    }

}
