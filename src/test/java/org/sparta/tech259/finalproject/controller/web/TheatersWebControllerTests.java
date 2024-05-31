package org.sparta.tech259.finalproject.controller.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.sparta.tech259.finalproject.model.entities.Address;
import org.sparta.tech259.finalproject.model.entities.Geo;
import org.sparta.tech259.finalproject.model.entities.Location;
import org.sparta.tech259.finalproject.model.entities.Theater;
import org.sparta.tech259.finalproject.model.exception.theater.TheaterWebAdvice;
import org.sparta.tech259.finalproject.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;

@WebMvcTest({TheatersWebController.class, TheaterWebAdvice.class})
class TheatersWebControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TheaterService theaterService;

    Theater theater1;
    Theater theater2;

    @BeforeEach
    public void setup() {
        theater1 = new Theater();
        theater1.setId("59a47286cfa9a3a73e51e72c");
        theater1.setTheaterId(1000);
        theater1.setLocation(new Location(
                new Address("340 W Market", "Bloomington", "MN", "55425"),
                new Geo("Point", new double[]{-93.24565, 44.85466})));

        theater2 = new Theater();
        theater2.setId("59a47286cfa9a3a73e51e72d");
        theater2.setTheaterId(1001);
        theater2.setLocation(new Location(
                new Address("123 Main St", "City", "ST", "12345"),
                new Geo("Point", new double[]{-93.0, 44.0})));
    }

    @Test
    @DisplayName("Get theaters returns a 200 status code")
    void getTheatersReturnsA200StatusCode() throws Exception {
        mockMvc.perform(get("/web/theaters"))
               .andExpectAll(
                       status().isOk(),
                       content().contentType("text/html;charset=UTF-8"));
    }

    @Test
    @DisplayName("Get theaters returns an web page containing theater data")
    void getTheatersReturnsAnWebPageContainingTheaterData() throws Exception {
        when(theaterService.getAllTheater())
                .thenReturn(List.of(theater1, theater2));

        mockMvc.perform(get("/web/theaters"))
                .andExpectAll(
                        content().string(containsString(theater1.getLocation().getAddress().getStreet1())),
                        content().string(containsString(theater2.getLocation().getAddress().getStreet1())));
    }

    @Test
    @DisplayName("Get theater by ID returns a web page containing data for the theater with that ID")
    void getTheaterByIdReturnsAWebPageContainingDataForTheTheaterWithThatId() throws Exception {
        when(theaterService.findByTheaterId(1000))
                .thenReturn(theater1);

        mockMvc.perform(get("/web/theater/1000"))
                .andExpectAll(
                        status().isOk(),
                        content().contentType("text/html;charset=UTF-8"),
                        content().string(containsString("1000"))
                );
    }

    @Test
    @DisplayName("Get theater by ID returns an error page with an informative message when no IDs match")
    void getTheaterByIdReturnsAnErrorPageWhenNoIDsMatch() throws Exception {
        when(theaterService.findByTheaterId(anyInt()))
                .thenReturn(null);

        mockMvc.perform(get("/web/theater/1000"))
                .andDo(print())
               .andExpectAll(
                       status().isBadRequest(),
                       content().contentType("text/html;charset=UTF-8"),
                       content().string(containsString("1000"))
               );
    }

    @Test
    @DisplayName("Get add theater form returns a web form populated with dummy data")
    void getAddTheaterFormReturnsAWebFormPopulatedWithDummyData() throws Exception {
        mockMvc.perform(get("/web/theater/add"))
                .andExpectAll(
                        status().isOk(),
                        content().string(containsString("0.0"))
                );
    }

    @Test
    @DisplayName("Add theater passes theater form data to the service layer")
    void addTheaterPassesTheaterFormDataToTheServiceLayer() throws Exception {
        ArgumentCaptor<Theater> theaterCaptor = ArgumentCaptor.forClass(Theater.class);

        mockMvc.perform(post("/web/theater/add").param("theaterId", "1000")
                                .param("address", "340 W Market")
                                .param("latitude", "45.0")
                                .param("longitude", "0.0"));

        verify(theaterService).createTheater(theaterCaptor.capture());
        Theater capturedTheater = theaterCaptor.getValue();

        assertEquals("340 W Market", capturedTheater.getLocation().getAddress().getStreet1());
    }

    @Test
    @DisplayName("Add theater passes latitude and longitude to the correct coordinate positions")
    void addTheaterPassesLatitudeAndLongitudeToTheCorrectCoordinatePositions() throws Exception {
        ArgumentCaptor<Theater> theaterCaptor = ArgumentCaptor.forClass(Theater.class);

        mockMvc.perform(post("/web/theater/add").param("theaterId", "1000")
                                                .param("address", "340 W Market")
                                                .param("latitude", "45.0")
                                                .param("longitude", "0.0"));

        verify(theaterService).createTheater(theaterCaptor.capture());
        Theater capturedTheater = theaterCaptor.getValue();

        assertEquals(0.0, capturedTheater.getLocation().getGeo().getCoordinates()[0]);
        assertEquals(45.0, capturedTheater.getLocation().getGeo().getCoordinates()[1]);
    }

    @Test
    @DisplayName("Delete theater by id passes the correct theater ID to the service layer")
    void deleteTheaterByIdPassesTheCorrectTheaterIdToTheServiceLayer() throws Exception {
        int theaterId = 1000;
        mockMvc.perform(get("/web/theater/delete/" + theaterId));
        verify(theaterService, times(1)).deleteTheater(theaterId);
    }

    @Test
    @DisplayName("Get edit theater form fetches the correct form and passes the required data to it")
    void getEditTheaterFormFetchesTheCorrectFormAndPassesTheRequiredDataToIt() throws Exception {
        int theaterId = 1000;
        when(theaterService.findByTheaterId(theaterId))
                .thenReturn(theater1);
        mockMvc.perform(get("/web/theater/edit/" + theaterId))
                .andExpectAll(
                        status().isOk(),
                        view().name("edit-theater"),
                        model().attributeExists("theater")
                );
    }

    @Test
    @DisplayName("Get edit theater form returns a form that is pre-populated with the correct data")
    void getEditTheaterFormReturnsAFormThatIsPrePopulatedWithTheCorrectData() throws Exception {
        int theaterId = 1000;
        when(theaterService.findByTheaterId(theaterId))
                .thenReturn(theater1);
        mockMvc.perform(get("/web/theater/edit/" + theaterId))
                .andExpectAll(
                        content().string(containsString(String.valueOf(theater1.getTheaterId()))),
                        content().string(containsString(theater1.getLocation().getAddress().getStreet1()))
                );
    }

    @Test
    @DisplayName("Edit theater passes the correct theater data to the service layer")
    void editTheaterPassesTheCorrectTheaterDataToTheServiceLayer() throws Exception {
        ArgumentCaptor<Theater> theaterCaptor = ArgumentCaptor.forClass(Theater.class);
        int theaterId = theater1.getTheaterId();

        mockMvc.perform(post("/web/theater/edit/" + theaterId)
                                .param("theaterId", String.valueOf(theaterId))
                                .param("city", theater1.getLocation().getAddress().getCity())
                                .param("latitude", "0.0")
                                .param("longitude", "100.0"));

        verify(theaterService).updateTheater(anyInt(), theaterCaptor.capture());
        Theater capturedTheater = theaterCaptor.getValue();

        assertAll(
                () -> assertEquals(theaterId, capturedTheater.getTheaterId()),
                () -> assertEquals(theater1.getLocation().getAddress().getCity(),
                                   capturedTheater.getLocation().getAddress().getCity()),
                () -> assertEquals(100.0, capturedTheater.getLocation().getGeo().getCoordinates()[0])
        );
    }
}