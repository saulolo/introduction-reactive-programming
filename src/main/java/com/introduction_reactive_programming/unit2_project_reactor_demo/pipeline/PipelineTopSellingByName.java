package com.introduction_reactive_programming.unit2_project_reactor_demo.pipeline;

import com.introduction_reactive_programming.unit2_project_reactor_demo.model.Videogame;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class PipelineTopSellingByName {

    /**
     * Filtra videojuegos más vendidos (totalSold >= 80) y retorna solo sus nombres.
     *
     * @param videogames Flux de videojuegos a procesar.
     * @return Flux<String> nombres de los videojuegos más vendidos.
     */
    public static Flux<String> getTopSellingByName(Flux<Videogame> videogames) {
        return videogames
                .filter(v -> v.getTotalSold() >= 80)
                .map(Videogame::getName)
                //.doOnNext(v -> log.info("[onNext]:{}", v))
                .doOnComplete(() -> log.info("[onComplte]: success"))
                .doOnError(err -> log.error("[Error]: " + err.getMessage()));
    }

}
