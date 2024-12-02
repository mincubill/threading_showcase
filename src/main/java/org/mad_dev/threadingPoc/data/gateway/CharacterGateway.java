package org.mad_dev.threadingPoc.data.gateway;

import org.mad_dev.threadingPoc.data.dto.CharacterDto;

public interface CharacterGateway {
    CharacterDto getCharacter(int id);
}
