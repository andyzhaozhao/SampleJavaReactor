package com.hoioy;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;

import java.io.IOException;

public class RXJava3ErrorOnErrorResumeNext {
    public static void main(String[] args) {
        subscribe();
    }

    private static void subscribe() {
        createObservableByCreate().onErrorResumeNext(new Function<Throwable, ObservableSource>() {
            @Override
            public ObservableSource apply(@NonNull Throwable throwable) throws Throwable {
                //出现错误时开始发射新的Observable的数据序列
                return Observable.just(1, 2, 3);
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
