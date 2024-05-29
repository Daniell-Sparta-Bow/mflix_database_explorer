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
        List<Theater> theaters = theaterService.getAllTheater()
                                                .stream()
                                                .filter(theater -> theater.getLocation() != null)
                                                .toList();
        model.addAttribute("theaters", theaters);
        return "theaters";
    }

    @GetMapping("/theaters/search")
    public String findTheatersBySearchParameters(@RequestParam("query") String query, Model model) {
        List<Theater> theaters = theaterService.findByState(query);
        theaters.addAll(theaterService.findByCity(query));
        theaters.addAll(theaterService.findByStreet(query));
        theaters.addAll(theaterService.findByZipcode(query));
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
    public String getAddTheaterForm(Model model) {
        model.addAttribute("theater", new Theater("", 0, new Location(new Address(), new Geo("Point", new double[] {0.0, 0.0}))));
        return "add-theater";
    }

    @PostMapping("theater/add")
    public String addTheater(@RequestParam("theaterId") Integer theaterId,
                             @RequestParam("address") String streetAddress,
                             @RequestParam("city") String city,
                             @RequestParam("state") String state,
                             @RequestParam("zipcode") String zipcode,
                             @RequestParam("latitude") double latitude,
                             @RequestParam("longitude") double longitude) {
        Theater newTheater = Theater.fromRequestParams(theaterId, streetAddress, city, state, zipcode, latitude, longitude);
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
        TheaterDto theaterDto = new TheaterDto(theater.getTheaterId(),
                                               theater.getLocation().getAddress().getStreet1(),
                                               theater.getLocation().getAddress().getCity(),
                                               theater.getLocation().getAddress().getState(),
                                               theater.getLocation().getAddress().getZipcode(),
                                               theater.getLocation().getGeo().getCoordinates()[1],
                                               theater.getLocation().getGeo().getCoordinates()[0]);
        model.addAttribute("theater", theaterDto);
        return "edit-theater";
    }

    @PostMapping("theater/edit/{id}")
    public String editTheater(@PathVariable Integer id,
                              @ModelAttribute("theater") TheaterDto theaterDto) {
        Theater theater = Theater.fromRequestParams(theaterDto.theaterId(), theaterDto.address(), theaterDto.city(), theaterDto.state(), theaterDto.zipcode(), theaterDto.latitude(),
                                                           theaterDto.longitude());
        System.out.println(theater);
        theaterService.updateTheater(id, theater);
        return "redirect:/web/theaters";
    }

    private Theater convertTheaterDTOToTheater(TheaterDto theaterDto){
        Address address = new Address(theaterDto.address(),
                                      theaterDto.city(),
                                      theaterDto.state(),
                                      theaterDto.zipcode());
        Geo geo = new Geo("Point", new double[] {theaterDto.latitude(), theaterDto.longitude()});
        Theater theater = new Theater();
        theater.setTheaterId(theaterDto.theaterId());
        theater.setLocation(new Location(address, geo));
        return theater;
    }
}
