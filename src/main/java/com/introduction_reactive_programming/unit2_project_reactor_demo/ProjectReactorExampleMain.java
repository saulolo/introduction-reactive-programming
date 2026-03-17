package com.introduction_reactive_programming.unit2_project_reactor_demo;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
public class ProjectReactorExampleMain {

    public static void main(String[] args) {

        System.out.println("=== MONO ===");
        //Publisher: de tipo Mono
        //doOnNext: Callback es una función que se ejecuta cuando sucede un determnado evento.
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

        System.out.println("=== COMBINAR 1 FLUX (flatMap) ===");
        Flux<String> fluxA = Flux.just("1", "2", "3", "4");
        Flux<String> fluxB = Flux.just("A", "B", "C", "D");

        Flux<String> combinedFlux = fluxA.flatMap(strA ->
                fluxB.map(strB -> strA + " - " + strB));

        combinedFlux.subscribe(System.out::println);

        System.out.println("=== COMBINAR 2 FLUX (merge) ===");
        //Los combina en orden de aparición segun la emision
        Flux<String> combinedFluxMerge = Flux.merge(fluxA, fluxB);
        combinedFluxMerge.subscribe(System.out::println);

        System.out.println("=== COMBINAR 3 FLUX (concat) ===");
        //Los combina tal cual primero un flux y apenas se complete sigue con el otro flux
        Flux<String> combinedFluxConcat = Flux.concat(fluxA, fluxB);
        combinedFluxConcat.subscribe(System.out::println);

        System.out.println("=== COMBINAR VARIOS FLUX (zip) ===");
        //Llamada a envios
        Flux<String> fluxShipments = Flux.just("Shipment1", "Shipment2", "Shipment3", "Shipment4").delayElements(Duration.ofMillis(120));
        //Llamada a la bodega
        Flux<String> fluxWarehouse = Flux.just("Warehouse1", "Warehouse2", "Warehouse3", "Warehouse4").delayElements(Duration.ofMillis(50));
        //Llamada a las pagos
        Flux<String> fluxPayments = Flux.just("Payments1", "Payments2", "Payments3", "Payments4").delayElements(Duration.ofMillis(150));
        //Llamada a las confirmaciones
        Flux<String> fluxConfirm = Flux.just("Confirm1", "Confirm2", "Confirm3", "Confirm4").delayElements(Duration.ofMillis(20));

        System.out.println("\n== Combinando fluxShipments con fluxWarehouse ==");
        //zip: Se une elemento de un flux con otro elemento del otro flux
        Flux<String> reportFluxShipmentAndWarehouse = Flux.zip(fluxShipments, fluxWarehouse, (shi, wha) -> shi + " <==> " + wha);

        reportFluxShipmentAndWarehouse
                .doOnNext(System.out::println)
                .blockLast();

        System.out.println("\n== Combinando fluxShipments, fluxWarehouse, fluxPayments y fluxConfirm ==");
        //El zip recibe varios constructores para combinar varios publisher
        Flux<String> reportSomeFlux = Flux.zip(fluxShipments, fluxWarehouse, fluxPayments, fluxConfirm)
                .map(tuple -> tuple.getT1() + " <==> " + tuple.getT2() + " <==> " + tuple.getT3() + " <==> " + tuple.getT4());

        reportSomeFlux
                .doOnNext(System.out::println)
                .blockLast();

        System.out.println("=== MANEJO DE EXCEPCIONES (handle) ===");
        Flux<String> languages = Flux.just("Java", "Python", "Goland", "JavaScript");



    }

}
