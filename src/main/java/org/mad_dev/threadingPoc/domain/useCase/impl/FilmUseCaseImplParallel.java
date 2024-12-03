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
import org.springframework.stereotype.Component;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class FilmUseCaseImplParallel implements FilmUseCase {
    private final FilmGateway gateway;
    private final FilmMapper mapper;
    private final CharacterGateway characterGateway;
    private final PlanetGateway planetGateway;
    private final CharacterMapper characterMapper;
    private static Logger LOG = LoggerFactory.getLogger(FilmUseCaseImplParallel.class);

    public FilmUseCaseImplParallel(FilmGateway gateway,
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
            Queue<Character> characters = new ConcurrentLinkedQueue<>();
                    filmDto.characters.parallelStream().forEach(characterUrl -> {
                    String characterId = UrlUtils.getId(characterUrl);
                    CharacterDto characterDto = characterGateway.getCharacter(Integer.parseInt(characterId));
                    String planetId = UrlUtils.getId(characterDto.getHomeworld());
                    PlanetDto planetDto = planetGateway.getPlanet(Integer.parseInt(planetId));
                    characters.add(characterMapper.mapToCharacter(characterDto, planetDto));
                    LOG.info("getting character - {}", characterId);

                });
            Film response = mapper.mapToFilm(gateway.getFilm(id));
            response.setCharacters(characters.stream().toList());
            LOG.info("listoco");
            return response;
        }
        catch (Exception ex) {
            LOG.error(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }
}
