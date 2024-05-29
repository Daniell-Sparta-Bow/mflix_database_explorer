package org.sparta.tech259.finalproject.model.entities;

public class Location {

    private Address address;
    private Geo geo;

    public Location(Address address, Geo geo) {
        this.address = address;
        this.geo = geo;
    }

    public Location() {
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Geo getGeo() {
        return geo;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }

    @Override
    public String toString() {
        return "Location{" +
                "address=" + address +
                ", geo=" + geo +
                '}';
    }
}
