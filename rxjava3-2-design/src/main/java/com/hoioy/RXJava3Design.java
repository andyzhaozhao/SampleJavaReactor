package com.hoioy;

import io.reactivex.rxjava3.core.Flowable;

public class RXJava3Design {
    public static void main(String[] args) {
        Flowable.just("Hello world").subscribe(System.out::println);

        hello("zhao","li");
    }

    public static void hello(String ... args){
        Flowable.fromArray(args).subscribe(s -> System.out.println("Hello " + s + "!"));
    }
}
