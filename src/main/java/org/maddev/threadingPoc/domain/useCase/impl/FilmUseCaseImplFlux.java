package org.maddev.threadingPoc.domain.useCase.impl;

import org.maddev.threadingPoc.data.dto.FilmDto;
import org.maddev.threadingPoc.data.dto.PlanetDto;
import org.maddev.threadingPoc.data.gateway.FilmGateway;
import org.maddev.threadingPoc.data.gateway.PlanetGateway;
import org.maddev.threadingPoc.data.gateway.impl.CharacterWebClientGateway;
import org.maddev.threadingPoc.domain.entities.Character;
import org.maddev.threadingPoc.domain.entities.Film;
import org.maddev.threadingPoc.domain.mapper.CharacterMapper;
import org.maddev.threadingPoc.domain.mapper.FilmMapper;
import org.maddev.threadingPoc.domain.useCase.FilmUseCase;
import org.maddev.threadingPoc.util.UrlUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FilmUseCaseImplFlux implements FilmUseCase {

    private final FilmGateway filmGateway;
    private final CharacterWebClientGateway characterGateway;
    private final PlanetGateway planetGateway;
    private final FilmMapper filmMapper;
    private final CharacterMapper characterMapper;

    public FilmUseCaseImplFlux(FilmGateway filmGateway,
                               CharacterWebClientGateway characterGateway,
                               PlanetGateway planetGateway,
                               FilmMapper filmMapper,
                               CharacterMapper characterMapper) {
        this.filmGateway = filmGateway;
        this.characterGateway = characterGateway;
        this.planetGateway = planetGateway;
        this.filmMapper = filmMapper;
        this.characterMapper = characterMapper;
    }

    @Override
    public Film getFilm(int id) {
        FilmDto filmDto = filmGateway.getFilm(id);
        List<Character> characters = new ArrayList<>();
        List<Integer> characterIds = new ArrayList<>();
        for(String characterUrl : filmDto.characters) {
            String characterId = UrlUtils.getId(characterUrl);
            characterIds.add(Integer.parseInt(characterId));
        }
        characterGateway.getCharacter(characterIds).forEach(
                c -> {
                    String planetId = UrlUtils.getId(c.getHomeworld());
                    PlanetDto planetDto = planetGateway.getPlanet(Integer.parseInt(planetId));
                    characters.add(characterMapper.mapToCharacter(c, planetDto));
                }
        );
        Film response = filmMapper.mapToFilm(filmDto);
        response.setCharacters(characters);
        return response;
    }

}
