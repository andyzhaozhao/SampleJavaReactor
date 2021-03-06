package com.hoioy;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import sun.rmi.runtime.Log;

import java.util.Arrays;
import java.util.List;

public class RXJava3Design2 {
    public static void main(String[] args) {
        subscribe();
    }

    public static void subscribe() {
        createObservableByCreate().subscribe(new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable disposable) {
                System.out.println(disposable.isDisposed());
            }

            @Override
            public void onNext(Object o) {
                System.out.println(o);
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("完成");
            }
        });
    }

    public static Observable createObservableByCreate() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> observableEmitter) throws Throwable {
            System.out.println("发送：：" + "hello");
                observableEmitter.onNext("hello");
                System.out.println("发送：：" + "world");
                observableEmitter.onNext("world");
                System.out.println("发送：：" + "onComplete");
                observableEmitter.onComplete();
            }
        });
    }
}
