package org.sparta.tech259.finalproject.model.entities.movies;

public class Awards{
    private Integer wins;
    private Integer nominations;
    private String text;
    private String lastUpdated;

    public Awards() {
    }

    public Awards(Integer wins, Integer nominations, String text) {
        this.wins = wins;
        this.nominations = nominations;
        this.text = text;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Integer getNominations() {
        return nominations;
    }

    public void setNominations(Integer nominations) {
        this.nominations = nominations;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Awards{" +
                "wins=" + wins +
                ", nominations=" + nominations +
                ", text='" + text + '\'' +
                ", lastUpdated='" + lastUpdated + '\'' +
                '}';
    }
}
