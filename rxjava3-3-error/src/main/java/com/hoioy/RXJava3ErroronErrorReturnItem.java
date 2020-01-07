package com.hoioy;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

import java.io.IOException;

public class RXJava3ErroronErrorReturnItem {
    public static void main(String[] args) {
        subscribe();
    }

    private static void subscribe() {
        createObservableByCreate().onErrorReturnItem(100).subscribe(new Observer() {
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
