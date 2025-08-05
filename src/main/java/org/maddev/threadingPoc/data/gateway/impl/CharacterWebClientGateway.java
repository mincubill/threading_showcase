package org.maddev.threadingPoc.data.gateway.impl;

import org.maddev.threadingPoc.data.dto.CharacterDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Service
    public class CharacterWebClientGateway   {

    private final WebClient characterClient;

    public CharacterWebClientGateway(@Qualifier("characterClient") WebClient characterClient) {
        this.characterClient = characterClient;
    }

    public List<CharacterDto> getCharacter(List<Integer> id) {
        List<CharacterDto> response = Flux.fromIterable(id)
                .flatMap(this::gatCharacterMono).toStream().toList();

        return response;
    }

    private Mono<CharacterDto> gatCharacterMono (int id) {
        return characterClient
                .get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(CharacterDto.class);
    }
}
