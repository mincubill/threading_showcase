package org.maddev.threadingPoc.data.gateway;

import org.maddev.threadingPoc.data.dto.PlanetDto;

public interface PlanetGateway {
    PlanetDto getPlanet(int id);
}
