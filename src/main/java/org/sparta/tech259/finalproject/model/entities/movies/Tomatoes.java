package org.sparta.tech259.finalproject.model.entities.movies;

import java.time.LocalDateTime;

public class Tomatoes{
    private Viewer viewer;
    private LocalDateTime dvd;
    private String website;
    private LocalDateTime lastUpdated;
    private Critic critic;

    public Tomatoes() {
    }

    public Tomatoes(Viewer viewer, LocalDateTime dvd, String website, LocalDateTime lastUpdated, Critic critic) {
        this.viewer = viewer;
        this.dvd = dvd;
        this.website = website;
        this.lastUpdated = lastUpdated;
        this.critic = critic;
    }

    public Critic getCritic() {
        return critic;
    }

    public void setCritic(Critic critic) {
        this.critic = critic;
    }

    public Viewer getViewer() {
        return viewer;
    }

    public void setViewer(Viewer viewer) {
        this.viewer = viewer;
    }

    public LocalDateTime getDvd() {
        return dvd;
    }

    public void setDvd(LocalDateTime dvd) {
        this.dvd = dvd;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        return "Tomatoes{" +
                "viewer=" + viewer +
                ", dvd=" + dvd +
                ", website='" + website + '\'' +
                ", lastUpdated=" + lastUpdated +
                ", critic=" + critic +
                '}';
    }
}