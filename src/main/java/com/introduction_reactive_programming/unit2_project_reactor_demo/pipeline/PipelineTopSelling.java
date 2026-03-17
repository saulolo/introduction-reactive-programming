package com.introduction_reactive_programming.unit2_project_reactor_demo.pipeline;

import com.introduction_reactive_programming.unit2_project_reactor_demo.model.Videogame;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class PipelineTopSelling {


    /**
     * Filtra y retorna un Flux con los videojuegos más vendidos (totalSold >= 80).
     *
     * @param videogames Flux de videojuegos a procesar.
     * @return Flux<Videogame> videojuegos que cumplen el filtro.
     */
    public static Flux<Videogame> getTopSelling(Flux<Videogame> videogames) {
        return videogames
                .filter(v -> v.getTotalSold() >= 80)
                .doOnNext(v -> log.info("[onNext]:" + v))
                .doOnComplete(() -> log.info("[onComplte]: success"))
                .doOnError(err -> log.error("[Error]: " + err.getMessage()));
    }

}
