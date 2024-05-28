package org.sparta.tech259.finalproject.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "movies")
public class Movie {

    @Id
    private ObjectId id;
    private String plot;
    private List<String> genre;
    private Integer runtime;
    private List <String> cast;
    private String poster;
    private String title;
    private String fullplot;
    private List<String> languages;
    private Integer released;
    private List<String> directors;
    private String rated;
    private Awards awards;
    private String lastUpdated;
    private Integer year;
    private Imdb imdb;
    private List<String> countries;
    private String type;
    private Tomatoes tomatoes;
    private Integer num_mflix_comments;

    public static class Awards{
        private int wins;
        private int nominations;
        private String text;
        private Date lastUpdated;

        public int getWins() {
            return wins;
        }

        public void setWins(int wins) {
            this.wins = wins;
        }

        public int getNominations() {
            return nominations;
        }

        public void setNominations(int nominations) {
            this.nominations = nominations;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Date getLastUpdated() {
            return lastUpdated;
        }

        public void setLastUpdated(Date lastUpdated) {
            this.lastUpdated = lastUpdated;
        }
    }

    public static class Imdb{
        private int id;
        private double rating;
        private int votes;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getRating() {
            return rating;
        }

        public void setRating(double rating) {
            this.rating = rating;
        }

        public int getVotes() {
            return votes;
        }

        public void setVotes(int votes) {
            this.votes = votes;
        }
    }

    public static class Tomatoes{
        private Viewer viewer;
        private Integer dvd;
        private String website;
        private Date lastUpdated;

        public Viewer getViewer() {
            return viewer;
        }

        public void setViewer(Viewer viewer) {
            this.viewer = viewer;
        }

        public Integer getDvd() {
            return dvd;
        }

        public void setDvd(Integer dvd) {
            this.dvd = dvd;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public Date getLastUpdated() {
            return lastUpdated;
        }

        public void setLastUpdated(Date lastUpdated) {
            this.lastUpdated = lastUpdated;
        }

        public static class Viewer{
            private double rating;
            private int numReviews;
            private int meter;

            public double getRating() {
                return rating;
            }

            public void setRating(double rating) {
                this.rating = rating;
            }

            public int getNumReviews() {
                return numReviews;
            }

            public void setNumReviews(int numReviews) {
                this.numReviews = numReviews;
            }

            public int getMeter() {
                return meter;
            }

            public void setMeter(int meter) {
                this.meter = meter;
            }
        }
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
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

    public Integer getReleased() {
        return released;
    }

    public void setReleased(Integer released) {
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
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
}






