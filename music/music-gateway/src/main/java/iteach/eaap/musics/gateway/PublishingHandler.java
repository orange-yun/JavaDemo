package iteach.eaap.musics.gateway;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PublishingHandler {
    public Mono<ServerResponse> getPublishings(ServerRequest serverRequest) {
        Flux<Publishing> publishings = WebClient.create().get()
                .uri("http://publishing-service:3300/publishings")
                .exchange()
                .flatMapMany(resp -> resp.bodyToFlux(Publishing.class));
        Flux<Music> musics = WebClient.create().get()
                .uri("http://management-service:3307/musics")
                .exchange()
                .flatMapMany(resp -> resp.bodyToFlux(Music.class));
        publishings = publishings.map(publishing -> {
            if (publishing.getMusicId() == null) {
                return publishing;
            }
            Music music = musics
                    .filter(a -> a.getId().equals(publishing.getMusicId()))
                    .blockFirst();
            publishing.setMusic(music);
            return publishing;
        });

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(publishings, Publishing.class));
    }

}
