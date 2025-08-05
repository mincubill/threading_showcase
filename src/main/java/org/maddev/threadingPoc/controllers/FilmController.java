package org.maddev.threadingPoc.controllers;

import org.maddev.threadingPoc.domain.entities.Film;
import org.maddev.threadingPoc.domain.useCase.FilmUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class FilmController {

    private final FilmUseCase useCase;
    private static final Logger LOG = LoggerFactory.getLogger(FilmController.class);

    public FilmController(@Qualifier("useCaseFlux") FilmUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping("/film/{id}")
    public ResponseEntity<Film> getFilm(@PathVariable int id) {
        return ResponseEntity.ok(useCase.getFilm(id));
    }


}
