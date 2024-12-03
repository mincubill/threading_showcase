package org.maddev.threadingPoc.domain.mapper;

import org.maddev.threadingPoc.data.dto.CharacterDto;
import org.maddev.threadingPoc.data.dto.PlanetDto;
import org.maddev.threadingPoc.domain.entities.Character;
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
