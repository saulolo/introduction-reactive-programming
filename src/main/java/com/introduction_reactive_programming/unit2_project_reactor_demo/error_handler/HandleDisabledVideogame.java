package com.introduction_reactive_programming.unit2_project_reactor_demo.error_handler;

import com.introduction_reactive_programming.unit2_project_reactor_demo.database.Database;
import com.introduction_reactive_programming.unit2_project_reactor_demo.model.Console;
import com.introduction_reactive_programming.unit2_project_reactor_demo.model.Videogame;
import reactor.core.publisher.Flux;

import java.util.List;

public class HandleDisabledVideogame {
    public static final Videogame DEFAULT_VIDEOGAME =
            Videogame.builder()
                    .name("Default")
                    .price(0.0)
                    .console(Console.ALL)
                    .reviews(List.of(
                    ))
                    .officialWebsite("https://www.default.com/")
                    .isDiscount(true)
                    .totalSold(0)
                    .build();


    public static Flux<Videogame> HandleDisabledVideogame(Flux<Videogame> videogames) {
        return videogames
                .handle((v, sink) -> {
                    if (Console.DISABLED == v.getConsole()) {
                        sink.error(new RuntimeException("Videojuego desahibilitado."));
                        return;
                    }
                    sink.next(v);
                })
                .onErrorResume(error -> {
                    System.out.println("Error detectado: " + error.getMessage());
                    return Flux.merge(
                            Database.getVideogamesFlux(),
                            Database.fluxAssassinsDefault
                    );
                })
                .cast(Videogame.class)
                .distinct(Videogame::getName);
    }

    public static Flux<Videogame> HandleDisabledVideogameDefault(Flux<Videogame> videogames) {
        return videogames
                .handle((v, sink) -> {
                    if (Console.DISABLED == v.getConsole()) {
                        sink.error(new RuntimeException("Videojuego desahibilitado."));
                        return;
                    }
                    sink.next(v);
                })
                .onErrorReturn(DEFAULT_VIDEOGAME)
                .cast(Videogame.class);
    }
}
