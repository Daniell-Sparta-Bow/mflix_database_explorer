package org.sparta.tech259.finalproject.model.entities.movies;

public class Viewer{
    private Double rating;
    private Integer numReviews;
    private Integer meter;

    public Viewer() {
    }

    public Viewer(double rating, Integer numReviews, Integer meter) {
        this.rating = rating;
        this.numReviews = numReviews;
        this.meter = meter;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Integer getNumReviews() {
        return numReviews;
    }

    public void setNumReviews(Integer numReviews) {
        this.numReviews = numReviews;
    }

    public Integer getMeter() {
        return meter;
    }

    public void setMeter(Integer meter) {
        this.meter = meter;
    }

    @Override
    public String toString() {
        return "Viewer{" +
                "rating=" + rating +
                ", numReviews=" + numReviews +
                ", meter=" + meter +
                '}';
    }
}

