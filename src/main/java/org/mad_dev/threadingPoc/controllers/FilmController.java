package org.mad_dev.threadingPoc.controllers;

import org.mad_dev.threadingPoc.domain.entities.Film;
import org.mad_dev.threadingPoc.domain.useCase.FilmUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class FilmController {

    private final FilmUseCase useCase;
    private static Logger LOG = LoggerFactory.getLogger(FilmController.class);

    public FilmController(@Qualifier("useCaseParallel") FilmUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping("/film/{id}")
    public ResponseEntity<Film> getFilm(@PathVariable int id) {
        LOG.info("llamada hecha por tfo");
        return ResponseEntity.ok(useCase.getFilm(id));
    }


}
