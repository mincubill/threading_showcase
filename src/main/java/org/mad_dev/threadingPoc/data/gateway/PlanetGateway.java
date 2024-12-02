package org.mad_dev.threadingPoc.data.gateway;

import org.mad_dev.threadingPoc.data.dto.PlanetDto;

public interface PlanetGateway {
    PlanetDto getPlanet(int id);
}
