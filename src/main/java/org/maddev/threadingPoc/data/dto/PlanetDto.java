package org.maddev.threadingPoc.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class PlanetDto {
    @JsonProperty("name")
    public String name;
    @JsonProperty("rotation_period")
    public String rotationPeriod;
    @JsonProperty("orbital_period")
    public String orbitalPeriod;
    @JsonProperty("diameter")
    public String diameter;
    @JsonProperty("climate")
    public String climate;
    @JsonProperty("gravity")
    public String gravity;
    @JsonProperty("terrain")
    public String terrain;
    @JsonProperty("surface_water")
    public String surfaceWater;
    @JsonProperty("population")
    public String population;
    @JsonProperty("residents")
    public List<String> residents;
    @JsonProperty("films")
    public List<String> films;
    @JsonProperty("created")
    public Date created;
    @JsonProperty("edited")
    public Date edited;
    @JsonProperty("url")
    public String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRotationPeriod() {
        return rotationPeriod;
    }

    public void setRotationPeriod(String rotationPeriod) {
        this.rotationPeriod = rotationPeriod;
    }

    public String getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public void setOrbitalPeriod(String orbitalPeriod) {
        this.orbitalPeriod = orbitalPeriod;
    }

    public String getDiameter() {
        return diameter;
    }

    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getGravity() {
        return gravity;
    }

    public void setGravity(String gravity) {
        this.gravity = gravity;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    public String getSurfaceWater() {
        return surfaceWater;
    }

    public void setSurfaceWater(String surfaceWater) {
        this.surfaceWater = surfaceWater;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public List<String> getResidents() {
        return residents;
    }

    public void setResidents(List<String> residents) {
        this.residents = residents;
    }

    public List<String> getFilms() {
        return films;
    }

    public void setFilms(List<String> films) {
        this.films = films;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getEdited() {
        return edited;
    }

    public void setEdited(Date edited) {
        this.edited = edited;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
