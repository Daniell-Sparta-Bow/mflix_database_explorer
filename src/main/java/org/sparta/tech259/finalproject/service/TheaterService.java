package org.sparta.tech259.finalproject.service;

import org.sparta.tech259.finalproject.model.entities.Theater;
import org.sparta.tech259.finalproject.model.exception.theater.TheaterIdAlreadyExistsException;
import org.sparta.tech259.finalproject.model.repositories.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TheaterService {
    private final TheaterRepository theaterRepository;

    @Autowired
    public TheaterService(TheaterRepository theaterRepository){
        this.theaterRepository = theaterRepository;
    }

    public List<Theater> getAllTheater(){
        return theaterRepository.findAll();
    }

    public Theater findByTheaterId(Integer id) {
        return theaterRepository.findByTheaterId(id).orElse(null);
    }

    public List<Theater> findByCity(String city) {
        return theaterRepository.findByLocationAddressCity(city);
    }

    public List<Theater> findByZipcode(String zipcode) {
        return theaterRepository.findByLocationAddressZipcode(zipcode);
    }

    public List<Theater> findByState(String state) {
        return theaterRepository.findByLocationAddressState(state);
    }

    public List<Theater> findByStreet(String street) {
        return theaterRepository.findByLocationAddressStreet1(street);
    }

    public boolean deleteTheater(Integer id){
        Optional<Theater> theater = theaterRepository.findByTheaterId(id);
        if(theater.isPresent()) {
            theaterRepository.delete(theater.get());
            return true;
        }
        return false;
    }

    public Theater createTheater(Theater theater){
        if (theaterRepository.findByTheaterId(theater.getTheaterId()).isPresent()) {
            throw new TheaterIdAlreadyExistsException(theater.getTheaterId());
        }
        return theaterRepository.save(theater);
    }

    public Theater updateTheater(Integer id, Theater newTheater) throws TheaterIdAlreadyExistsException {
        Optional<Theater> oldTheater = theaterRepository.findByTheaterId(id);
        if(oldTheater.isPresent()){
            Theater updatedTheater = oldTheater.get();
            if(newTheater.getTheaterId() != id && theaterRepository.findByTheaterId(newTheater.getTheaterId()).isPresent()){
                throw new TheaterIdAlreadyExistsException(id);
            }
            updatedTheater.setTheaterId(newTheater.getTheaterId());
            updatedTheater.setLocation(newTheater.getLocation());
            return theaterRepository.save(updatedTheater);
        }
        return null;
    }
}
