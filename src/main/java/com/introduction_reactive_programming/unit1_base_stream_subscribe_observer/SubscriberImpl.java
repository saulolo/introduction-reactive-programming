package com.introduction_reactive_programming.unit1_base_stream_subscribe_observer;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

import java.util.function.Function;

@Log
@AllArgsConstructor
public class SubscriberImpl<T> implements Subscriber<T> {

    private final Function<T, String> mapper;
    private final String name;


    @Override
    public void onNext(T next) {
        final var valueMapped = mapper.apply(next);
        log.info("[onNext]" + next);
        log.info("[onNext] mapped" + valueMapped);

    }

    @Override
    public String getName() {
        return name;
    }
}
