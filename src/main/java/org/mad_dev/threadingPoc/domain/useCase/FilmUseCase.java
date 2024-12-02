package org.mad_dev.threadingPoc.domain.useCase;

import org.mad_dev.threadingPoc.domain.entities.Film;

public interface FilmUseCase {
    Film getFilm(int id);
}
