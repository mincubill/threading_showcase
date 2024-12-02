package org.mad_dev.threadingPoc.domain.mapper;

import org.mad_dev.threadingPoc.data.dto.CharacterDto;
import org.mad_dev.threadingPoc.data.dto.PlanetDto;
import org.mad_dev.threadingPoc.domain.entities.Character;
import org.springframework.stereotype.Component;

@Component
public class CharacterMapper {
    public Character mapToCharacter (CharacterDto characterDto, PlanetDto planetDto) {
        return Character.CharacterBuilder.builder()
                .name(characterDto.getName())
                .gender(characterDto.getGender())
                .homeworld(planetDto.getName())
                .build();
    }
}
