package com.hoioy;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.BooleanSupplier;

import java.io.IOException;

public class RXJava3ErrorRetryUntil {
    public static void main(String[] args) {
        subscribe();
    }

    private static void subscribe() {
        createObservableByCreate().retryUntil(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() throws Throwable {
                //true 不重试 。false，重试
                return true;
            }
        }).subscribe(new Observer() {
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
                observableEmitter.onNext("hello");
                observableEmitter.onNext("world");

                observableEmitter.onError(new IOException("发生了IO异常"));

                observableEmitter.onNext("1");
                observableEmitter.onNext("2");

                observableEmitter.onComplete();
            }
        });
    }
}
