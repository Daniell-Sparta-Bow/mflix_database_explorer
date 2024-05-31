package org.sparta.tech259.finalproject.model.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "theaters")
public class Theater {

    @Id
    private String id;
    private int theaterId;
    private Location location;

    public Theater(String id, int theaterId, Location location) {
        this.id = id;
        this.theaterId = theaterId;
        this.location = location;
    }

    public Theater(){
    }

    public static Theater fromRequestParams(Integer theaterId, String streetAddress, String city, String state, String zipcode, Double latitude, Double longitude) {
        Address address = new Address(streetAddress, city, state, zipcode);
        Geo geo = new Geo("Point", new double[] {longitude, latitude});
        Theater theater = new Theater();
        theater.setTheaterId(theaterId);
        theater.setLocation(new Location(address, geo));
        return theater;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Theater{" +
                "id='" + id + '\'' +
                ", theaterId=" + theaterId +
                ", location=" + location +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Theater theater = (Theater) o;
        return theaterId == theater.theaterId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(theaterId);
    }
}
