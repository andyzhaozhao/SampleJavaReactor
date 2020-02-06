package com.hoioy;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class RXJava3cast {
    public static void main(String[] args) {
        subscribe();
    }

    public static void subscribe() {
        createObservable()
                .cast(Integer.class)
                .subscribe(new Observer() {
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

    public static Observable createObservable() {
        return Observable.fromArray(1, 2, 3);
    }
}
