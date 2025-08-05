package org.maddev.threadingPoc.domain.useCase.impl;

import org.maddev.threadingPoc.data.dto.FilmDto;
import org.maddev.threadingPoc.data.gateway.FilmGateway;
import org.maddev.threadingPoc.domain.entities.Character;
import org.maddev.threadingPoc.domain.entities.Film;
import org.maddev.threadingPoc.domain.mapper.FilmMapper;
import org.maddev.threadingPoc.domain.useCase.FilmUseCase;
import org.maddev.threadingPoc.service.GetCharacterAsync;
import org.maddev.threadingPoc.util.UrlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

@Component
public class FilmUseCaseImplAsync implements FilmUseCase {

    private final FilmGateway gateway;
    private final FilmMapper mapper;
    private final GetCharacterAsync characterAsync;
    private static final  Logger LOG = LoggerFactory.getLogger(FilmUseCaseImplAsync.class);

    public FilmUseCaseImplAsync(FilmGateway gateway,
                                FilmMapper mapper,
                                GetCharacterAsync characterAsync) {
        this.gateway = gateway;
        this.mapper = mapper;
        this.characterAsync = characterAsync;
    }


    @Override
    public Film getFilm(int id) {
        try {
            FilmDto filmDto = gateway.getFilm(id);
            Queue<Character> characters = new ConcurrentLinkedQueue<>();
            ExecutorService executorService = Executors.newWorkStealingPool();
            List<CompletableFuture<Void>> futures = new ArrayList<>();

            filmDto.characters.forEach( characterUrl -> {
                CompletableFuture<Void> future = CompletableFuture.runAsync(() ->
                {
                    String characterId = UrlUtils.getId(characterUrl);
                    CompletableFuture<Character> characterDto = characterAsync.getCharacterAsync(characterId);
                    try {
                        characters.add(characterDto.get());
                    } catch (InterruptedException | ExecutionException e) {
                        LOG.error(e.getMessage());
                        Thread.currentThread().interrupt();
                        throw new RuntimeException(e);
                    }
                    LOG.info("getting character - {}", characterId);

                }, executorService);
                futures.add(future);
            });

            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
            executorService.shutdown();

            Film response = mapper.mapToFilm(gateway.getFilm(id));
            response.setCharacters(characters.stream().toList());

            return response;
        }
        catch (Exception ex) {
            LOG.error(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }


}
