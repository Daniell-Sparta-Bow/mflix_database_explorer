package org.sparta.tech259.finalproject.model.entities;

public record TheaterDto(Integer theaterId, String address, String city, String state, String zipcode, Double latitude, Double longitude) {

    public Theater toEntity() {
        return Theater.fromRequestParams(theaterId(), address(), city(), state(), zipcode(), latitude(),
                                         longitude());
    }

    public static TheaterDto of(Theater theater) {
        return new TheaterDto(theater.getTheaterId(),
                              theater.getLocation().getAddress().getStreet1(),
                              theater.getLocation().getAddress().getCity(),
                              theater.getLocation().getAddress().getState(),
                              theater.getLocation().getAddress().getZipcode(),
                              theater.getLocation().getGeo().getCoordinates()[1],
                              theater.getLocation().getGeo().getCoordinates()[0]);
    }

    public static TheaterDto of() {
        return new TheaterDto(0, "", "", "", "", 0.0, 0.0);
    }
}
