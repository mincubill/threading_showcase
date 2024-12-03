package org.maddev.threadingPoc.service;

import org.maddev.threadingPoc.data.dto.CharacterDto;
import org.maddev.threadingPoc.data.dto.PlanetDto;
import org.maddev.threadingPoc.data.gateway.CharacterGateway;
import org.maddev.threadingPoc.data.gateway.PlanetGateway;
import org.maddev.threadingPoc.domain.entities.Character;
import org.maddev.threadingPoc.domain.mapper.CharacterMapper;
import org.maddev.threadingPoc.util.UrlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class GetCharacterAsync {

    private final CharacterGateway characterGateway;
    private final PlanetGateway planetGateway;
    private final CharacterMapper characterMapper;
    private static final  Logger LOG = LoggerFactory.getLogger(GetCharacterAsync.class);

    public GetCharacterAsync(CharacterGateway characterGateway,
                             PlanetGateway planetGateway,
                             CharacterMapper characterMapper) {
        this.characterGateway = characterGateway;
        this.planetGateway = planetGateway;
        this.characterMapper = characterMapper;
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
