package org.maddev.threadingPoc.data.gateway.impl;

import org.maddev.threadingPoc.data.dto.CharacterDto;
import org.maddev.threadingPoc.data.gateway.CharacterGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;


@Repository
public class CharacterGatewayImpl implements CharacterGateway {

    private final RestTemplate restTemplate;
    private final String baseUrl;
    private final String endpoint;
    private static Logger LOG = LoggerFactory.getLogger(CharacterGatewayImpl.class);

    public CharacterGatewayImpl(RestTemplate restTemplate,
                                @Value("${gateway.baseUrl}") String baseUrl,
                                @Value("${gateway.characterEndpoint}")String endpoint) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
        this.endpoint = endpoint;
    }

    @Override
    public CharacterDto getCharacter(int id) {
        try{
            String url = baseUrl.concat(endpoint).concat(String.valueOf(id));
            return restTemplate.getForObject(url, CharacterDto.class);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e.getMessage());

        }

    }
}
