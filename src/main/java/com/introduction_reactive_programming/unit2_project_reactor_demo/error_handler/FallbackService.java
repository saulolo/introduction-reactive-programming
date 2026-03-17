package com.introduction_reactive_programming.unit2_project_reactor_demo.error_handler;

import com.introduction_reactive_programming.unit2_project_reactor_demo.database.Database;
import com.introduction_reactive_programming.unit2_project_reactor_demo.model.Console;
import com.introduction_reactive_programming.unit2_project_reactor_demo.model.Videogame;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class FallbackService {


    public static Flux<Videogame> calFallback(Flux<Videogame> videogames) {
        return videogames
                .handle((v, sink) -> {
                    if (Console.DISABLED == v.getConsole()) {
                        sink.error(new RuntimeException("Videojuego desahibilitado."));
                        return;
                    }
                    sink.next(v);
                })
                .retry(5)
                .onErrorResume(error -> {
                    log.error("La BD esta fallando: " + error.getMessage());
                    return Database.fluxFallBack;
                })
                .repeat(1)
                .cast(Videogame.class);
    }



}
