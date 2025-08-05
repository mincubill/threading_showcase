package org.maddev.threadingPoc.common.config;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.socket.ConnectionSocketFactory;
import org.apache.hc.client5.http.socket.PlainConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.http.URIScheme;
import org.apache.hc.core5.http.config.Registry;
import org.apache.hc.core5.http.config.RegistryBuilder;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.maddev.threadingPoc.data.gateway.CharacterGateway;
import org.maddev.threadingPoc.data.gateway.FilmGateway;
import org.maddev.threadingPoc.data.gateway.PlanetGateway;
import org.maddev.threadingPoc.data.gateway.impl.CharacterWebClientGateway;
import org.maddev.threadingPoc.domain.mapper.CharacterMapper;
import org.maddev.threadingPoc.domain.mapper.FilmMapper;
import org.maddev.threadingPoc.domain.useCase.FilmUseCase;
import org.maddev.threadingPoc.domain.useCase.impl.FilmUseCaseImpl;
import org.maddev.threadingPoc.domain.useCase.impl.FilmUseCaseImplAsync;
import org.maddev.threadingPoc.domain.useCase.impl.FilmUseCaseImplFlux;
import org.maddev.threadingPoc.domain.useCase.impl.FilmUseCaseImplParallel;
import org.maddev.threadingPoc.service.GetCharacterAsync;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import javax.net.ssl.SSLContext;
import java.net.http.HttpClient;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

@Configuration
public class BeanConfig {

    @Bean("filmClient")
    public WebClient filmClient(WebClient client,
                                @Value("${gateway.baseUrl}") String baseUrl,
                                @Value("${gateway.filmEndpoint}") String endpoint) {
        return client.mutate()
                .baseUrl(baseUrl.concat(endpoint)).build();
    }

    @Bean("characterClient")
    public WebClient characterClient (WebClient client,
                                      @Value("${gateway.baseUrl}") String baseUrl,
                                      @Value("${gateway.characterEndpoint}") String endpoint) {
        return client.mutate()
                .baseUrl(baseUrl.concat(endpoint))
                .build();
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

    @Bean("useCaseFlux")
    public FilmUseCase useCaseFlux (FilmGateway filmGateway,
                                    CharacterWebClientGateway characterGateway,
                                    PlanetGateway planetGateway,
                                    FilmMapper filmMapper,
                                    CharacterMapper characterMapper) {
        return new FilmUseCaseImplFlux(filmGateway, characterGateway, planetGateway, filmMapper, characterMapper);
    }

}
