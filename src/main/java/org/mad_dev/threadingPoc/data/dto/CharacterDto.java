package org.mad_dev.threadingPoc.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class CharacterDto {
    @JsonProperty("name")
    public String name;
    @JsonProperty("height")
    public String height;
    @JsonProperty("mass")
    public String mass;
    @JsonProperty("hair_color")
    public String hairColor;
    @JsonProperty("skin_color")
    public String skinColor;
    @JsonProperty("eye_color")
    public String eyeColor;
    @JsonProperty("birth_year")
    public String birthYear;
    @JsonProperty("gender")
    public String gender;
    @JsonProperty("homeworld")
    public String homeworld;
    @JsonProperty("films")
    public List<String> films;
    @JsonProperty("species")
    public List<Object> species;
    @JsonProperty("vehicles")
    public List<String> vehicles;
    @JsonProperty("starships")
    public List<String> starships;
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

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(String skinColor) {
        this.skinColor = skinColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHomeworld() {
        return homeworld;
    }

    public void setHomeworld(String homeworld) {
        this.homeworld = homeworld;
    }

    public List<String> getFilms() {
        return films;
    }

    public void setFilms(List<String> films) {
        this.films = films;
    }

    public List<Object> getSpecies() {
        return species;
    }

    public void setSpecies(List<Object> species) {
        this.species = species;
    }

    public List<String> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<String> vehicles) {
        this.vehicles = vehicles;
    }

    public List<String> getStarships() {
        return starships;
    }

    public void setStarships(List<String> starships) {
        this.starships = starships;
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
