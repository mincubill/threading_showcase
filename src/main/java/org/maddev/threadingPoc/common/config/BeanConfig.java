package org.maddev.threadingPoc.common.config;

import org.maddev.threadingPoc.data.gateway.CharacterGateway;
import org.maddev.threadingPoc.data.gateway.FilmGateway;
import org.maddev.threadingPoc.data.gateway.PlanetGateway;
import org.maddev.threadingPoc.domain.mapper.CharacterMapper;
import org.maddev.threadingPoc.domain.mapper.FilmMapper;
import org.maddev.threadingPoc.domain.useCase.FilmUseCase;
import org.maddev.threadingPoc.domain.useCase.impl.FilmUseCaseImpl;
import org.maddev.threadingPoc.domain.useCase.impl.FilmUseCaseImplAsync;
import org.maddev.threadingPoc.domain.useCase.impl.FilmUseCaseImplParallel;
import org.maddev.threadingPoc.service.GetCharacterAsync;
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
                                    GetCharacterAsync getCharacterAsync) {
        return new FilmUseCaseImplAsync(gateway, mapper, getCharacterAsync);
    }
}
