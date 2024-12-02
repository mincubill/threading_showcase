package org.mad_dev.threadingPoc.data.gateway;

import org.mad_dev.threadingPoc.data.dto.FilmDto;

public interface FilmGateway {
    FilmDto getFilm(int id);
}
