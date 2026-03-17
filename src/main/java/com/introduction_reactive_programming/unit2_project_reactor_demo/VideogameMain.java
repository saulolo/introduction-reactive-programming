package com.introduction_reactive_programming.unit2_project_reactor_demo;

import com.introduction_reactive_programming.unit2_project_reactor_demo.calbacks.CallbacksExample;
import com.introduction_reactive_programming.unit2_project_reactor_demo.database.Database;
import com.introduction_reactive_programming.unit2_project_reactor_demo.error_handler.FallbackService;
import com.introduction_reactive_programming.unit2_project_reactor_demo.error_handler.HandleDisabledVideogame;
import com.introduction_reactive_programming.unit2_project_reactor_demo.model.Videogame;
import com.introduction_reactive_programming.unit2_project_reactor_demo.pipeline.PipelineAllComments;
import com.introduction_reactive_programming.unit2_project_reactor_demo.pipeline.PipelineSumAllPricesInDiscount;
import com.introduction_reactive_programming.unit2_project_reactor_demo.pipeline.PipelineTopSelling;
import com.introduction_reactive_programming.unit2_project_reactor_demo.pipeline.PipelineTopSellingByName;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class VideogameMain {


    public static void main(String[] args) {

        //Lista reactiva de videojuegos
        Flux<Videogame> database = Database.getVideogamesFlux();

        final long[] counter = {0};
        final long[] counterTopName = {0};
        final long[] counterDiscount = {0};
        final long[] counterReviews = {0};

        System.out.println("=== VIDEOJUEGOS MAS VENDIDOS ===");

        //Pipeline de los mas vendidios
        Flux<Videogame> top = PipelineTopSelling.getTopSelling(database);

        //subscribe
        //top.subscribe(System.out::println);

        top.subscribe(
                videogame -> {
                    System.out.println(videogame);
                    counter[0]++;
                },
                error -> log.error("[Error]: " + error.getMessage()),
                () -> System.out.println(
                        "==============================" +
                                "\n  🎮 Total Top de ventas: " + counter[0] +
                                "\n==============================")
        );

        System.out.println("--------------------------------------------------------------------\n");
        System.out.println("=== VIDEOJUEGOS MAS VENDIDOS POR NOMBRE ===");

        //Pipeline de los videjuegos mas vendidos filtrado por nombre por nombre
        Flux<String> topByName = PipelineTopSellingByName.getTopSellingByName(database);

        topByName.subscribe(
                videogame -> {
                    System.out.println("🎮 " + videogame);
                    counterTopName[0]++;
                },
                error -> log.error("[Error]: " + error.getMessage()),
                () -> System.out.println(
                        "\n==============================" +
                                "\n  🎮 Total Top de ventas: " + counterTopName[0] +
                                "\n==============================")
        );

        System.out.println("--------------------------------------------------------------------\n");
        System.out.println("=== TOTAL VIDEOJUEGOS CON DESCUENTO ===");

        //Pipeline del total de videojuegos con descuento
        Mono<Double> totalDiscount = PipelineSumAllPricesInDiscount.getSumAllPricesInDiscount(database);
        totalDiscount.subscribe(
                videogame -> {
                    System.out.println(videogame);
                    counterDiscount[0]++;
                },
                error -> log.error("[Error]: " + error.getMessage()),
                () -> System.out.println(
                        "\n==============================" +
                                "\n  🎮 Total juegos con descuento: " + counterDiscount[0] +
                                "\n==============================")

        );

        System.out.println("--------------------------------------------------------------------\n");
        System.out.println("=== COMENTARIOS ===");

        //Pipeline para ver todos los comentarios
        PipelineAllComments.getAllReviewsComments(database)
                .subscribe(
                        videogame -> {
                            System.out.println("💬 " + videogame);
                            counterReviews[0]++;
                        },
                        error -> log.error("[Error]: " + error.getMessage()),
                        () -> System.out.println(
                                "==============================" +
                                        "\n  🎮 Total reviews: " + counterReviews[0] +
                                        "\n==============================")

                );

        System.out.println("--------------------------------------------------------------------\n");
        System.out.println("=== GENERANDO UNA EXCEPCIÓN ===");
        Flux<Videogame> handle = HandleDisabledVideogame.HandleDisabledVideogame(database);
        //handle.subscribe(System.out::println);

        System.out.println("--------------------------------------------------------------------\n");
        System.out.println("=== GENERANDO UNA EXCEPCIÓN CON RETURN ===");
        Flux<Videogame> handleReturn = HandleDisabledVideogame.HandleDisabledVideogameDefault(database);
        //handle.subscribe(System.out::println);

        System.out.println("--------------------------------------------------------------------\n");
        System.out.println("=== RETRY y REPEAT ===");

        Flux<Videogame> handleRe = FallbackService.calFallback(database);
        //handleRe.subscribe(videogame -> log.info(videogame.toString()));

        System.out.println("--------------------------------------------------------------------\n");
        System.out.println("=== CALLBACKS ===");

        Flux<Videogame> calledFallback = CallbacksExample.callbacks(database);
        calledFallback.subscribe();

    }

}
