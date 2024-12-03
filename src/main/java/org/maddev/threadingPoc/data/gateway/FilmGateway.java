package org.maddev.threadingPoc.data.gateway;

import org.maddev.threadingPoc.data.dto.FilmDto;

public interface FilmGateway {
    FilmDto getFilm(int id);
}
