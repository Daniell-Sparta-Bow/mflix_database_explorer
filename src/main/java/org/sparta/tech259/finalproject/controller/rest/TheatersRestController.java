package org.sparta.tech259.finalproject.controller.rest;

import org.sparta.tech259.finalproject.model.exception.theater.TheaterIdDoesNotExistsException;
import org.sparta.tech259.finalproject.service.TheaterService;
import org.sparta.tech259.finalproject.model.entities.Theater;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class TheatersRestController {
    private final TheaterService theaterService;

    public TheatersRestController(TheaterService theaterService) {
        this.theaterService = theaterService;
    }

    @GetMapping("/theaters")
    public List<Theater> getAllTheaters() {
        return theaterService.getAllTheater();
    }

    @GetMapping("/theater/{id}")
    public Theater getTheaterById(@PathVariable Integer id) {
        Optional<Theater> findTheaterById = Optional.ofNullable(theaterService.findByTheaterId(id));
        if (findTheaterById.isPresent()) {
            return findTheaterById.get();
        }
        throw new TheaterIdDoesNotExistsException(id);
    }

    @GetMapping("/theater")
    public List<Theater> getTheatersByCity(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String zipcode,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String street) {

        if (city != null) {
            return theaterService.findByCity(city);
        } else if (zipcode != null) {
            return theaterService.findByZipcode(zipcode);
        } else if (state != null) {
            return theaterService.findByState(state);
        } else if (street != null) {
            return theaterService.findByStreet(street);
        } else {
            return List.of();
        }
    }

    @GetMapping("/test/theaters")
    public List<Theater> getTheaters(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String zipcode,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String street) {
        ArrayList<Theater> result = new ArrayList<>(theaterService.getAllTheater());

        if (city != null) {
            result.retainAll(theaterService.findByCity(city));
        }
        if (zipcode != null) {
            result.retainAll(theaterService.findByZipcode(zipcode));
        }
        if (state != null) {
            result.retainAll(theaterService.findByState(state));
        }
        if (street != null) {
            result.retainAll(theaterService.findByStreet(street));
        }
        return result;
    }

    // Long, Lat
    @GetMapping("/theater/distance/")
    public List<Theater> theatersInRange(@RequestParam float longitude,
                                  @RequestParam float latitude,
                                  @RequestParam float distance){
        List<Theater> theaters = new ArrayList<>();
        for(Theater theater : theaterService.getAllTheater()){
            if(calculateDistance(latitude,longitude,
                                 theater.getLocation().getGeo().getCoordinates()[1],
                                 theater.getLocation().getGeo().getCoordinates()[0]) < distance){
                theaters.add(theater);
            }
        }
        return theaters;
    }

    @PostMapping("/theater")
    public String addTheater(@Valid @RequestParam Optional<Theater> theater) {
        if (theater.isEmpty()) {
            return null;
        }
        else {
            theaterService.createTheater(theater.get());
            return "Theater has been created";
        }
    }

    @PutMapping("/theater/{id}")
    public Theater updateTheater(@PathVariable Integer id,
                                 @RequestBody Theater theater){
        if(theaterService.updateTheater(id, theater) != null){
            return theaterService.findByTheaterId(id);
        }
        throw new TheaterIdDoesNotExistsException(id);
    }

    @DeleteMapping("/theater/{id}")
    public Boolean deleteTheater(@PathVariable Integer id){
        if(theaterService.deleteTheater(id)){
            return true;
        }
        throw new TheaterIdDoesNotExistsException(id);
    }

    private double haversine(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }

    private double calculateDistance(double startLat, double startLong, double endLat, double endLong) {

        double dLat = Math.toRadians((endLat - startLat));
        double dLong = Math.toRadians((endLong - startLong));

        startLat = Math.toRadians(startLat);
        endLat = Math.toRadians(endLat);

        double a = haversine(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversine(dLong);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return 6371 * c;
    }
}
