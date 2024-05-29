package org.sparta.tech259.finalproject.model.entities.movies;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "movies")
public class Movie {

    @Id
    private String _id;
    private String plot;
    private List<String> genres;
    private Integer runtime;
    private List <String> cast;
    private String poster;
    private String title;
    private String fullplot;
    private List<String> languages;
    private LocalDateTime released;
    private List<String> directors;
    private String rated;
    private Awards awards;
    private String lastUpdated;
    private String year;
    private Imdb imdb;
    private List<String> countries;
    private String type;
    private Tomatoes tomatoes;
    private Integer num_mflix_comments;

    public Movie() {
    }

    public Movie(String _id, String plot, List<String> genres, Integer runtime, List<String> cast, String poster, String title, String fullplot, List<String> languages, LocalDateTime released, List<String> directors, String rated, Awards awards, String lastUpdated, String year, Imdb imdb, List<String> countries, String type, Tomatoes tomatoes, Integer num_mflix_comments) {
        this._id = _id;
        this.plot = plot;
        this.genres = genres;
        this.runtime = runtime;
        this.cast = cast;
        this.poster = poster;
        this.title = title;
        this.fullplot = fullplot;
        this.languages = languages;
        this.released = released;
        this.directors = directors;
        this.rated = rated;
        this.awards = awards;
        this.lastUpdated = lastUpdated;
        this.year = year;
        this.imdb = imdb;
        this.countries = countries;
        this.type = type;
        this.tomatoes = tomatoes;
        this.num_mflix_comments = num_mflix_comments;
    }



    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public List<String> getGenre() {
        return genres;
    }

    public void setGenre(List<String> genres) {
        this.genres = genres;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public List<String> getCast() {
        return cast;
    }

    public void setCast(List<String> cast) {
        this.cast = cast;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFullplot() {
        return fullplot;
    }

    public void setFullplot(String fullplot) {
        this.fullplot = fullplot;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public LocalDateTime getReleased() {
        return released;
    }

    public void setReleased(LocalDateTime released) {
        this.released = released;
    }

    public List<String> getDirectors() {
        return directors;
    }

    public void setDirectors(List<String> directors) {
        this.directors = directors;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public Awards getAwards() {
        return awards;
    }

    public void setAwards(Awards awards) {
        this.awards = awards;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Imdb getImdb() {
        return imdb;
    }

    public void setImdb(Imdb imdb) {
        this.imdb = imdb;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Tomatoes getTomatoes() {
        return tomatoes;
    }

    public void setTomatoes(Tomatoes tomatoes) {
        this.tomatoes = tomatoes;
    }

    public Integer getNum_mflix_comments() {
        return num_mflix_comments;
    }

    public void setNum_mflix_comments(Integer num_mflix_comments) {
        this.num_mflix_comments = num_mflix_comments;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "_id=" + _id +
                ", plot='" + plot + '\'' +
                ", genres=" + genres +
                ", runtime=" + runtime +
                ", cast=" + cast +
                ", poster='" + poster + '\'' +
                ", title='" + title + '\'' +
                ", fullplot='" + fullplot + '\'' +
                ", languages=" + languages +
                ", released=" + released +
                ", directors=" + directors +
                ", rated='" + rated + '\'' +
                ", awards=" + awards +
                ", lastUpdated='" + lastUpdated + '\'' +
                ", year=" + year +
                ", imdb=" + imdb +
                ", countries=" + countries +
                ", type='" + type + '\'' +
                ", tomatoes=" + tomatoes +
                ", num_mflix_comments=" + num_mflix_comments +
                '}';
    }
}






