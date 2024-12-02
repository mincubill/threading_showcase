package org.mad_dev.threadingPoc.domain.useCase.impl;

import org.mad_dev.threadingPoc.data.dto.CharacterDto;
import org.mad_dev.threadingPoc.data.dto.FilmDto;
import org.mad_dev.threadingPoc.data.dto.PlanetDto;
import org.mad_dev.threadingPoc.data.gateway.CharacterGateway;
import org.mad_dev.threadingPoc.data.gateway.FilmGateway;
import org.mad_dev.threadingPoc.data.gateway.PlanetGateway;
import org.mad_dev.threadingPoc.domain.entities.Character;
import org.mad_dev.threadingPoc.domain.entities.Film;
import org.mad_dev.threadingPoc.domain.mapper.CharacterMapper;
import org.mad_dev.threadingPoc.domain.mapper.FilmMapper;
import org.mad_dev.threadingPoc.domain.useCase.FilmUseCase;
import org.mad_dev.threadingPoc.util.UrlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class FilmUseCaseImplAsync implements FilmUseCase {

    private final FilmGateway gateway;
    private final FilmMapper mapper;
    private final CharacterGateway characterGateway;
    private final PlanetGateway planetGateway;
    private final CharacterMapper characterMapper;
    private static Logger LOG = LoggerFactory.getLogger(FilmUseCaseImplAsync.class);

    public FilmUseCaseImplAsync(FilmGateway gateway,
                                FilmMapper mapper,
                                CharacterGateway characterGateway,
                                PlanetGateway planetGateway,
                                CharacterMapper characterMapper) {
        this.gateway = gateway;
        this.mapper = mapper;
        this.characterGateway = characterGateway;
        this.planetGateway = planetGateway;
        this.characterMapper = characterMapper;
    }


    @Override
    public Film getFilm(int id) {
        try {
            FilmDto filmDto = gateway.getFilm(id);
            List<Character> characters = new ArrayList<>();
            for(String characterUrl : filmDto.characters) {
                String characterId = UrlUtils.getId(characterUrl);
                CompletableFuture<Character> characterDto = getCharacterAsync(characterId);
                characters.add(characterDto.get());
            }
            Film response = mapper.mapToFilm(gateway.getFilm(id));
            response.setCharacters(characters);

            return response;
        }
        catch (Exception ex) {
            LOG.error(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Async
    public CompletableFuture<Character> getCharacterAsync (String characterId) {
        try{
            CharacterDto characterDto = characterGateway.getCharacter(Integer.parseInt(characterId));
            String planetId = UrlUtils.getId(characterDto.getHomeworld());
            PlanetDto planetDto = planetGateway.getPlanet(Integer.parseInt(planetId));
            return CompletableFuture.completedFuture(characterMapper.mapToCharacter(characterDto, planetDto));
        }
        catch (Exception ex) {
            LOG.error(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }


}
