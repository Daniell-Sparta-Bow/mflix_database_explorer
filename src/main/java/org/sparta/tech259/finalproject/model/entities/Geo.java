package org.sparta.tech259.finalproject.model.entities;

import java.util.Arrays;

public class Geo {

    private String type;
    private double[] coordinates;

    public Geo(String type, double[] coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }

    public Geo() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[] coordinates) {
        if (coordinates.length != 2) {
            throw new RuntimeException("Coordinates must be of length 2");
        }
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "Geo{" +
                "type='" + type + '\'' +
                ", coordinates=" + Arrays.toString(coordinates) +
                '}';
    }
}
