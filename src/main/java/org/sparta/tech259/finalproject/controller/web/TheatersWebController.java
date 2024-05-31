package org.sparta.tech259.finalproject.controller.web;

import org.sparta.tech259.finalproject.model.entities.*;
import org.sparta.tech259.finalproject.model.exception.theater.TheaterIdDoesNotExistsException;
import org.sparta.tech259.finalproject.service.TheaterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class TheatersWebController {
    private final TheaterService theaterService;

    public TheatersWebController(TheaterService theaterService) {
        this.theaterService = theaterService;
    }

    @GetMapping("/theaters")
    public String getAllTheaters(Model model) {
        List<Theater> theaters = theaterService.getAllTheater()
                                                .stream()
                                                .filter(theater -> theater.getLocation() != null)
                                                .toList();
        model.addAttribute("theaters", theaters);
        return "theaters";
    }

    @GetMapping("/theaters/search")
    public String findTheatersBySearchParameters(@RequestParam("query") String query, Model model) {
        Set<Theater> theaters = new LinkedHashSet<>();
        if (query.isBlank()) {
            theaters.addAll(theaterService.getAllTheater());
        } else {
            theaters.addAll(theaterService.findByState(query));
            theaters.addAll(theaterService.findByCity(query));
            theaters.addAll(theaterService.findByStreet(query));
            theaters.addAll(theaterService.findByZipcode(query));
        }
        model.addAttribute("theaters", theaters);
        return "theaters";
    }

    @GetMapping("/theater/{id}")
    public String getTheaterById(@PathVariable Integer id, Model model) {
        Theater theater = theaterService.findByTheaterId(id);
        if (theater == null) {
            throw new TheaterIdDoesNotExistsException(id);
        }
        model.addAttribute("theater", theater);
        return "theater";
    }

    @GetMapping("/theater/add")
    public String getAddTheaterForm(Model model) {
        model.addAttribute("theater", TheaterDto.of());
        return "theater-add";
    }

    @PostMapping("theater/add")
    public String addTheater(@ModelAttribute("theater") TheaterDto theaterDto) {
        Theater newTheater = theaterDto.toEntity();
        theaterService.createTheater(newTheater);
        return "redirect:/theaters";
    }

    @GetMapping("/theater/delete/{id}")
    public String deleteTheaterByTheaterId(@PathVariable Integer id) {
        theaterService.deleteTheater(id);
        return "redirect:/theaters";
    }

    @GetMapping("/theater/edit/{id}")
    public String getEditTheaterForm(@PathVariable Integer id, Model model) {
        Theater theater = theaterService.findByTheaterId(id);
        TheaterDto theaterDto = TheaterDto.of(theater);
        model.addAttribute("theater", theaterDto);
        return "theater-edit";
    }

    @PostMapping("theater/edit/{id}")
    public String editTheater(@PathVariable Integer id,
                              @ModelAttribute("theater") TheaterDto theaterDto) {
        Theater theater = theaterDto.toEntity();
        theaterService.updateTheater(id, theater);
        return "redirect:/theaters";
    }
}
