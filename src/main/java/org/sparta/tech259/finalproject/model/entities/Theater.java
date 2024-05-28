package org.sparta.tech259.finalproject.model.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
}
