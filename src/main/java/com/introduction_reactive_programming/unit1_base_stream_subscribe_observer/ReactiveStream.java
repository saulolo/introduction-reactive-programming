package com.introduction_reactive_programming.unit1_base_stream_subscribe_observer;

import lombok.extern.java.Log;

import java.util.LinkedList;
import java.util.List;

@Log
public class ReactiveStream<T> {

    private final List<Subscriber<T>> subscribers = new LinkedList<>();

    public ReactiveStream<T> subscribe(Subscriber<T> subscriber) {
        subscribers.add(subscriber);
        log.info("[subscribe]" + subscriber.getName());
        return this;
    }

    public void unsubscribe(Subscriber<T> subscriber) {
        subscribers.remove(subscriber);
        log.info("[Unsubscribe]" + subscriber.getName());
    }

    public void emit(T value) {
        subscribers.forEach(sub -> sub.onNext(value));
    }
}
