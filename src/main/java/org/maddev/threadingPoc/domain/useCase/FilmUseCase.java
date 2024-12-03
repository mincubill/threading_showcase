package org.maddev.threadingPoc.domain.useCase;

import org.maddev.threadingPoc.domain.entities.Film;

public interface FilmUseCase {
    Film getFilm(int id);
}
