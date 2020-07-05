package iteach.eaap.musics.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class MusicGatewayConfiguration {
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("music management service", predicate -> predicate
                        .path("/api/musics", "/api/musics/**")
                        .filters(filter -> filter.stripPrefix(1))
                        .uri("http://management-service:3307"))
                .route("music publishing service", predicate -> predicate
                        .path("/api/publishings").and()
                        .method(HttpMethod.POST)
                        .filters(filter -> filter.stripPrefix(1))
                        .uri("http://publishing-service:3300"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> orderHandlerRouting(PublishingHandler handler) {
        return RouterFunctions.route(GET("/api/publishings"), handler::getPublishings);
    }
}
