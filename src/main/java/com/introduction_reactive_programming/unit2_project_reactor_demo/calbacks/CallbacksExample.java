package com.introduction_reactive_programming.unit2_project_reactor_demo.calbacks;

import com.introduction_reactive_programming.unit2_project_reactor_demo.model.Videogame;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class CallbacksExample {

    //Todos los callbacks empiezan con do; Ejemplo ==> doOnSubscribe, doOnRequest
    public static Flux<Videogame> callbacks(Flux<Videogame> videogames) {
        return videogames
                .doOnSubscribe(sub -> log.info("[doOnSubscribe]: {}", sub)) // Se ejecuta cuando un Subscriber se suscribe al flujo. Útil para logs o inicialización.
                .doOnRequest(value -> log.info("[doOnRequest]: {}", value)) // Se llama cuando el Subscriber solicita elementos (request(n), backpressure).
                .doOnNext(videogame -> log.info("[doOnRequest]: {}", videogame.getName())) //Se ejecuta cada vez que un nuevo elemento es emitido por el Publisher. Útil para depuración o logs.
                .doOnCancel(() -> log.warn("[doOnCancel]")) //Se ejecuta si el flujo se cancela antes de completarse.
                .doOnError(err -> log.error("[doOnError]: {}", err.getMessage())) //Se ejecuta si ocurre un error.
                .doOnComplete(() -> log.info(("[doOnComplete]: success"))) //Se ejecuta cuando el flujo finaliza con éxito.
                .doOnTerminate(() -> log.info(("[doOnTerminate]"))) //Se ejecuta cuando el flujo termina, sea por éxito o error. Es similar a doOnComplete + doOnError.
                .doFinally(signalType -> log.warn("[doFinally]: {}", signalType)); //Se ejecuta al final del flujo, indicando la razón (CANCEL, COMPLETE, ERROR).
    }
}
