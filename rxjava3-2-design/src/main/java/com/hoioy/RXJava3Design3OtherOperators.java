package com.hoioy;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;

public class RXJava3Design3OtherOperators {
    public static void main(String[] args) {
        subscribe();
    }

    //4.使用操作符转换被观察者发送的数据.md
    public static void subscribe() {
        createObservableByCreate().skip(10).take(5).map(o -> "被修改的："+o).subscribe(new Observer() {
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
                for (int i = 0; i < 50; i++) {
                    observableEmitter.onNext("hello：" + i);
                }
                observableEmitter.onComplete();
            }
        });
    }
}
