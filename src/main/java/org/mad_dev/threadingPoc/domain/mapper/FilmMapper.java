package org.mad_dev.threadingPoc.domain.mapper;

import org.mad_dev.threadingPoc.data.dto.FilmDto;
import org.mad_dev.threadingPoc.domain.entities.Film;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.UUID;

@Component
public class FilmMapper {
    public Film mapToFilm (FilmDto input) {
        return Film.FilmBuilder.builder()
                .filmId(UUID.randomUUID())
                .name(input.getTitle())
                .characterCount(String.valueOf(input.getCharacters().size()))
                .characters(new ArrayList<>())
                .build();
    }
}
