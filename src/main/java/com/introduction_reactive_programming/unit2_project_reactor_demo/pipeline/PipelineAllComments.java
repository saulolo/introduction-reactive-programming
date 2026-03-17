package com.introduction_reactive_programming.unit2_project_reactor_demo.pipeline;

import com.introduction_reactive_programming.unit2_project_reactor_demo.model.Review;
import com.introduction_reactive_programming.unit2_project_reactor_demo.model.Videogame;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class PipelineAllComments {


    /**
     * Extrae todos los comentarios de las reviews de todos los videojuegos.
     *
     * @param videogames Flux de videojuegos a procesar.
     * @return Flux<String> secuencia de comentarios de las reviews.
     */
    public static Flux<String> getAllReviewsComments(Flux<Videogame> videogames) {
        return videogames
                .flatMap(videogame -> Flux.fromIterable(videogame.getReviews()))
                .map(Review::getComment)
                //.doOnNext(v -> log.info("[onNext]:" + v))
                .doOnComplete(() -> log.info("[onComplte]: success"))
                .doOnError(err -> log.error("[Error]: " + err.getMessage()));
    }
    //flatMap: se utiliza para dos flujos

}
