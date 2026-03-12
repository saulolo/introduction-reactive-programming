package com.introduction_reactive_programming.unit2_project_reactor_demo;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class ProjectReactorMain {

    public static void main(String[] args) {

        System.out.println("=== MONO ===");
        //Publisher: de tipo Mono
        //doOnNext: Callback es una función que se ejecuta cuando sucede un determnado enevto.
        Mono<String> stringMono = Mono.just("Hola Muundo.")
                .doOnNext(value -> log.info("[onNext]: "))
                .doOnSuccess(value -> log.info("[Success]: " + value))
                .doOnError(err -> log.info("[error]: " + err.getMessage()));

        //Consumer
        stringMono.subscribe(
                data -> log.info("Recibiendo datos: " + data),
                err -> log.error("Error: " + err.getMessage()),
                () -> log.info("Complete success.")
        );


        System.out.println("=== FLUX ===");
        //Publisher: de tipo Flux
        Flux<String> stringFlux = Flux.just("Aleja", "Pipe", "Saulolo", "Pupilo")
                .doOnNext(value -> System.out.println("[Apodo]: " + value))
                .doOnComplete(() -> log.info("[onComplete]: success")) //el doOnComplete solo recibe un valor
                .doOnError(err -> log.error("[Error]: " + err.getMessage()));

        //Consumer
        stringFlux.subscribe(
                data -> log.info("Recibiendo datos del flux: " + data),
                err -> log.error("Error: " + err.getMessage()),
                () -> log.info("Complete success.")
        );





    }

}
