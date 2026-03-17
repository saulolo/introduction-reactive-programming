package com.introduction_reactive_programming.unit2_project_reactor_demo;

import com.introduction_reactive_programming.unit2_project_reactor_demo.calbacks.CallbacksExample;
import com.introduction_reactive_programming.unit2_project_reactor_demo.database.Database;
import com.introduction_reactive_programming.unit2_project_reactor_demo.model.Videogame;
import reactor.core.publisher.Flux;

public class CallbacksMain {

    public static void main(String[] args) {

        //Lista reactiva de videojuegos
        Flux<Videogame> database = Database.getVideogamesFlux();

        CallbacksExample.callbacks(database)
                .subscribe();
    }
}
