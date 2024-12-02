package org.mad_dev.threadingPoc.data.gateway.impl;

import org.mad_dev.threadingPoc.data.dto.PlanetDto;
import org.mad_dev.threadingPoc.data.gateway.PlanetGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class PlanetGatewayImpl implements PlanetGateway {

    private final RestTemplate restTemplate;
    private final String baseUrl;
    private final String endpoint;
    private static Logger LOG = LoggerFactory.getLogger(PlanetGatewayImpl.class);

    public PlanetGatewayImpl(RestTemplate restTemplate,
                             @Value("${gateway.baseUrl}") String baseUrl,
                             @Value("${gateway.planetEndpoint}") String endpoint) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
        this.endpoint = endpoint;
    }

    @Override
    public PlanetDto getPlanet(int id) {
        try {
            String url = baseUrl.concat(endpoint).concat(String.valueOf(id));
            return restTemplate.getForObject(url, PlanetDto.class);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

    }
}
