package org.maddev.threadingPoc.data.gateway;

import org.maddev.threadingPoc.data.dto.CharacterDto;

public interface CharacterGateway {
    CharacterDto getCharacter(int id);
}
