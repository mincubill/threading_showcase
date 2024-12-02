package org.mad_dev.threadingPoc.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

public class Film {
    @JsonProperty("id")
    private UUID filmId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("characters_count")
    private String characterCount;
    @JsonProperty("characters")
    private List<Character> characters;

    public UUID getFilmId() {
        return filmId;
    }

    public void setFilmId(UUID filmId) {
        this.filmId = filmId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacterCount() {
        return characterCount;
    }

    public void setCharacterCount(String characterCount) {
        this.characterCount = characterCount;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }


    public static final class FilmBuilder {
        private UUID filmId;
        private String name;
        private String characterCount;
        private List<Character> characters;

        private FilmBuilder() {
        }

        public static FilmBuilder builder() {
            return new FilmBuilder();
        }

        public FilmBuilder filmId(UUID filmId) {
            this.filmId = filmId;
            return this;
        }

        public FilmBuilder name(String name) {
            this.name = name;
            return this;
        }

        public FilmBuilder characterCount(String characterCount) {
            this.characterCount = characterCount;
            return this;
        }

        public FilmBuilder characters(List<Character> characters) {
            this.characters = characters;
            return this;
        }

        public Film build() {
            Film film = new Film();
            film.setFilmId(filmId);
            film.setName(name);
            film.setCharacterCount(characterCount);
            film.setCharacters(characters);
            return film;
        }
    }
}
