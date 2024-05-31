package org.sparta.tech259.finalproject.model.entities.movies;

public class Critic {
    private Integer  meter;
    private Integer  numReviews;
    private Double rating;

    public Critic() {
    }

    public Critic(Integer meter, Integer numReviews, double rating) {
        this.meter = meter;
        this.numReviews = numReviews;
        this.rating = rating;
    }

    public Integer getMeter() {
        return meter;
    }

    public void setMeter(Integer meter) {
        this.meter = meter;
    }

    public Integer getNumReviews() {
        return numReviews;
    }

    public void setNumReviews(Integer numReviews) {
        this.numReviews = numReviews;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Critic{" +
                "meter=" + meter +
                ", numReviews=" + numReviews +
                ", rating=" + rating +
                '}';
    }
}
