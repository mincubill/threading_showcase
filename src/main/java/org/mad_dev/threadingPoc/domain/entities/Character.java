package org.mad_dev.threadingPoc.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Character {
    @JsonProperty("name")
    private String name;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("homeworld")
    private String homeworld;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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


    public static final class CharacterBuilder {
        private String name;
        private String gender;
        private String homeworld;

        private CharacterBuilder() {
        }

        public static CharacterBuilder builder() {
            return new CharacterBuilder();
        }

        public CharacterBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CharacterBuilder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public CharacterBuilder homeworld(String homeworld) {
            this.homeworld = homeworld;
            return this;
        }

        public Character build() {
            Character character = new Character();
            character.setName(name);
            character.setGender(gender);
            character.setHomeworld(homeworld);
            return character;
        }
    }
}
