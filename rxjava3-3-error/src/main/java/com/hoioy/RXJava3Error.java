package com.hoioy;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.Arrays;
import java.util.List;

public class RXJava3Error {
    public static void main(String[] args) {
        subscribe();
    }

    public static void subscribe() {
        createObservable().subscribe(new Observer() {
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
        //just和from会异步触发订阅者的onNext()方法。每一个item都会被这个被观察者发送出去，最后会异步触发订阅者的onCompleted()方法。
        Observable<String> o = Observable.fromArray("a", "b", "c");

        List list = Arrays.asList(1, 2, 3, 4);
        Observable<Integer> o1 = Observable.fromIterable(list);

        Observable<String> o2 = Observable.just("one object");

        return o;
    }
}
