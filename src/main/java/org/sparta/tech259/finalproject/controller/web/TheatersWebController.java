package org.sparta.tech259.finalproject.controller.web;

import org.sparta.tech259.finalproject.model.entities.*;
import org.sparta.tech259.finalproject.service.TheaterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/web")
public class TheatersWebController {
    private final TheaterService theaterService;

    public TheatersWebController(TheaterService theaterService) {
        this.theaterService = theaterService;
    }

    @GetMapping("/theaters")
    public String getAllTheaters(Model model) {
        List<Theater> theaters = theaterService.getAllTheater();
        model.addAttribute("theaters", theaters);
        return "theaters";
    }

    @GetMapping("/theater/{id}")
    public String getTheaterById(@PathVariable Integer id, Model model) {
        Theater theater = theaterService.findByTheaterId(id);
        model.addAttribute("theater", theater);
        return "theater";
    }

    @GetMapping("/theater/add")
    public String getAddTheaterForm() {
        return "add-theater";
    }

    @PostMapping("theater/add")
    public String addTheater(@RequestParam("theaterId") Integer updatedId,
                             @RequestParam("address") String streetAddress,
                             @RequestParam("city") String city,
                             @RequestParam("state") String state,
                             @RequestParam("zipcode") String zipcode,
                             @RequestParam("latitude") double latitude,
                             @RequestParam("longitude") double longitude) {
        Theater newTheater = Theater.fromRequestParams(updatedId, streetAddress, city, state, zipcode, latitude, longitude);
        theaterService.createTheater(newTheater);
        return "redirect:/web/theaters";
    }

    @GetMapping("/theater/delete/{id}")
    public String deleteTheaterByTheaterId(@PathVariable Integer id) {
        theaterService.deleteTheater(id);
        return "redirect:/web/theaters";
    }

    @GetMapping("/theater/edit/{id}")
    public String getEditTheaterForm(@PathVariable Integer id, Model model) {
        Theater theater = theaterService.findByTheaterId(id);
        model.addAttribute("theater", theater);
        return "edit-theater";
    }

    @PostMapping("/theater/edit/{id}")
    public String editTheater(@PathVariable Integer id,
                              @RequestParam("theaterId") Integer updatedId,
                              @RequestParam("address") String streetAddress,
                              @RequestParam("city") String city,
                              @RequestParam("state") String state,
                              @RequestParam("zipcode") String zipcode,
                              @RequestParam("latitude") double latitude,
                              @RequestParam("longitude") double longitude) {
        Theater updatedTheater = Theater.fromRequestParams(updatedId, streetAddress, city, state, zipcode, latitude, longitude);
        theaterService.updateTheater(id, updatedTheater);
        return "redirect:/web/theaters";
    }
}
