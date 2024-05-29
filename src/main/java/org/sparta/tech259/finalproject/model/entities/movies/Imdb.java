package org.sparta.tech259.finalproject.model.entities.movies;

public class Imdb{
    private Integer id;
    private Double rating;
    private Integer votes;

    public Imdb() {
    }

    public Imdb(Integer id, double rating, Integer votes) {
        this.id = id;
        this.rating = rating;
        this.votes = votes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "Imdb{" +
                "id=" + id +
                ", rating=" + rating +
                ", votes=" + votes +
                '}';
    }
}
