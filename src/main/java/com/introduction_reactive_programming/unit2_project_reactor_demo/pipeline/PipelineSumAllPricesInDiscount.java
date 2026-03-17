package com.introduction_reactive_programming.unit2_project_reactor_demo.pipeline;

import com.introduction_reactive_programming.unit2_project_reactor_demo.model.Videogame;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class PipelineSumAllPricesInDiscount {


    /**
     * Suma los precios de todos los videojuegos que están en descuento.
     *
     * @param videogames Flux de videojuegos a procesar.
     * @return Mono<Double> suma total de los precios en descuento.
     */
    public static Mono<Double> getSumAllPricesInDiscount(Flux<Videogame> videogames) {
        return videogames
                .filter(Videogame::getIsDiscount)
                .map(Videogame::getPrice)
                .reduce(Double::sum)
                //.doOnNext(v -> log.info("[onNext]:" + v))
                .doOnSuccess(V  -> log.info("[onComplte]: success"))
                .doOnError(err -> log.error("[Error]: " + err.getMessage()));
    }

}
