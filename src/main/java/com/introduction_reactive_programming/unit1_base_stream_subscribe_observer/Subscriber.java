package com.introduction_reactive_programming.unit1_base_stream_subscribe_observer;

public interface Subscriber<T> {

    void onNext(T next);

    String getName();

}
