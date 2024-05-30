package org.sparta.tech259.finalproject.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.sparta.tech259.finalproject.model.entities.Address;
import org.sparta.tech259.finalproject.model.entities.Geo;
import org.sparta.tech259.finalproject.model.entities.Location;
import org.sparta.tech259.finalproject.model.entities.Theater;
import org.sparta.tech259.finalproject.model.exception.theater.TheaterIdAlreadyExistsException;
import org.sparta.tech259.finalproject.model.repositories.TheaterRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class TheaterServiceTests {

    TheaterRepository repository;
    TheaterService theaterService;
    Theater theater1 = new Theater();
    Theater theater2 = new Theater();

    @BeforeEach
    public void setUp() {
        repository = mock();
        theaterService = new TheaterService(repository);

        theater1.setId("59a47286cfa9a3a73e51e72c");
        theater1.setTheaterId(1000);
        theater1.setLocation(new Location(
                new Address("340 W Market", "Bloomington", "MN", "55425"),
                new Geo("Point", new double[]{-93.24565, 44.85466})));

        theater2.setId("59a47286cfa9a3a73e51e72d");
        theater2.setTheaterId(1001);
        theater2.setLocation(new Location(
                new Address("123 Main St", "City", "ST", "12345"),
                new Geo("Point", new double[]{-93.0, 44.0})));
    }

    @Test
    @DisplayName("When no matching ID, deleteTheater returns false")
    void whenNoMatchingIdDeleteTheaterReturnsFalse() {
        when(repository.findByTheaterId(100))
                .thenReturn(Optional.empty());
        Assertions.assertFalse(theaterService.deleteTheater(100));
    }

    @Test
    @DisplayName("When a matching ID is found, deleteTheater returns true")
    void whenAMatchingIdIsFoundDeleteTheaterReturnsTrue() {
        Theater theater = new Theater("id", 100, new Location());
        when(repository.findByTheaterId(100))
                .thenReturn(Optional.of(theater));

        Assertions.assertTrue(theaterService.deleteTheater(100));
    }

    @Test
    public void testGetAllTheater() {
        List<Theater> theaters = Arrays.asList(theater1, theater2);
        when(theaterService.getAllTheater()).thenReturn(theaters);

        List<Theater> foundTheaters = theaterService.getAllTheater();

        verify(repository, times(1)).findAll();
        Assertions.assertEquals(2, foundTheaters.size());
    }

    @Test
    @DisplayName("When no matching ID is found, updateTheater returns null")
    void whenNoMatchingIdIsFoundUpdateTheaterReturnsNull() {
        when(repository.findByTheaterId(anyInt()))
                .thenReturn(Optional.empty());

        Assertions.assertNull(theaterService.updateTheater(100, theater1));
    }

    @Test
    @DisplayName("When a matching ID is found and the theater ID is not being updated, updateTheater returns the new theater")
    void whenAMatchingIdIsFoundAndTheTheaterIdIsNotBeingUpdatedUpdateTheaterReturnsTheNewTheater() {
        when(repository.findByTheaterId(theater1.getTheaterId()))
                .thenReturn(Optional.of(theater1));

        when(repository.save(theater1))
                .thenReturn(theater1);

        Assertions.assertEquals(theater1, theaterService.updateTheater(theater1.getTheaterId(), theater1));
    }

    @Test
    @DisplayName("When attempting to update a theater to an ID that already exists, updateTheater throws an exception")
    void whenAttemptingToUpdateATheaterToAnIdThatAlreadyExistsUpdateTheaterThrowsAnException() {
        when(repository.findByTheaterId(anyInt()))
                .thenReturn(Optional.of(theater1));

        int theaterId = 100;

        Assertions.assertThrows(TheaterIdAlreadyExistsException.class, () -> theaterService.updateTheater(theaterId, theater1));
    }

    @Test
    @DisplayName("When attempting to update a theater to a theater ID that does not exist, updateTheater returns the theater with updated theater ID")
    void whenAttemptingToUpdateATheaterToATheaterIdThatDoesNotExistUpdateTheaterReturnsTheTheaterWithUpdatedId() {
        int theaterId = 100;
        Theater storedTheater = new Theater("id", theaterId, new Location());

        when(repository.findByTheaterId(anyInt()))
                .thenReturn(Optional.of(storedTheater))
                .thenReturn(Optional.empty());

        when(repository.save(any(Theater.class)))
                .then(AdditionalAnswers.returnsFirstArg());

        Assertions.assertEquals(theater1.getTheaterId(), theaterService.updateTheater(theaterId, theater1).getTheaterId());
    }

    @Test
    @DisplayName("Update theater correctly updates location")
    void updateTheaterCorrectlyUpdatesLocation() {
        int theaterId = theater1.getTheaterId();
        Theater storedTheater = new Theater("id", theaterId, new Location());

        when(repository.findByTheaterId(anyInt()))
                .thenReturn(Optional.of(storedTheater))
                .thenReturn(Optional.empty());

        when(repository.save(any(Theater.class)))
                .then(AdditionalAnswers.returnsFirstArg());

        Assertions.assertEquals(theater1.getLocation(), theaterService.updateTheater(theaterId, theater1).getLocation());
    }


    @Test
    public void testCreateTheater() {
        when(theaterService.createTheater(theater1)).thenReturn(theater1);

        Theater createdTheater = theaterService.createTheater(theater1);

        assertNotNull(createdTheater);
        assertEquals(theater1.getId(), createdTheater.getId());
        assertEquals(theater1.getTheaterId(), createdTheater.getTheaterId());
        assertEquals(theater1.getLocation(), createdTheater.getLocation());

        verify(repository, times(1)).save(theater1);
    }

}