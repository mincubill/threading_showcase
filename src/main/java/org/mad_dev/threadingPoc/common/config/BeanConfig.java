package org.mad_dev.threadingPoc.common.config;

import org.mad_dev.threadingPoc.data.gateway.CharacterGateway;
import org.mad_dev.threadingPoc.data.gateway.FilmGateway;
import org.mad_dev.threadingPoc.data.gateway.PlanetGateway;
import org.mad_dev.threadingPoc.domain.mapper.CharacterMapper;
import org.mad_dev.threadingPoc.domain.mapper.FilmMapper;
import org.mad_dev.threadingPoc.domain.useCase.FilmUseCase;
import org.mad_dev.threadingPoc.domain.useCase.impl.FilmUseCaseImpl;
import org.mad_dev.threadingPoc.domain.useCase.impl.FilmUseCaseImplAsync;
import org.mad_dev.threadingPoc.domain.useCase.impl.FilmUseCaseImplParallel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfig {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean("useCaseSync")
    public FilmUseCase useCaseSync(FilmGateway gateway,
                                   FilmMapper mapper,
                                   CharacterGateway characterGateway,
                                   PlanetGateway planetGateway,
                                   CharacterMapper characterMapper) {
        return new FilmUseCaseImpl(gateway, mapper, characterGateway, planetGateway, characterMapper);
    }

    @Bean("useCaseParallel")
    public FilmUseCase useCaseParallel(FilmGateway gateway,
                                   FilmMapper mapper,
                                   CharacterGateway characterGateway,
                                   PlanetGateway planetGateway,
                                   CharacterMapper characterMapper) {
        return new FilmUseCaseImplParallel(gateway, mapper, characterGateway, planetGateway, characterMapper);
    }

    @Bean("useCaseAsync")
    public FilmUseCase useCaseAsync(FilmGateway gateway,
                                       FilmMapper mapper,
                                       CharacterGateway characterGateway,
                                       PlanetGateway planetGateway,
                                       CharacterMapper characterMapper) {
        return new FilmUseCaseImplAsync(gateway, mapper, characterGateway, planetGateway, characterMapper);
    }
}
