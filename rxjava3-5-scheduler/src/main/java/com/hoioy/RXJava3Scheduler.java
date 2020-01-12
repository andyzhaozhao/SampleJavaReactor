package com.hoioy;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class RXJava3Scheduler {
    public static void main(String[] args) throws IOException, InterruptedException {
        subscribe();
    }

    private static void subscribe() {
        createObservableByCreate().subscribe(new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable disposable) {
                System.out.println("onSubscribe");
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


        Observable<String> o1 = Observable.interval(2, 1, TimeUnit.SECONDS, Schedulers.trampoline()).map(new Function<Long, String>() {
            @Override
            public String apply(@NonNull Long aLong) throws Throwable {
                return "用户名" + aLong;
            }
        });
        Observable<String> o2 = Observable.interval(2, 1, TimeUnit.SECONDS).map(new Function<Long, String>() {
            @Override
            public String apply(@NonNull Long aLong) throws Throwable {
                return "邮箱" + aLong;
            }
        });

        return Observable.combineLatest(o1, o2, new BiFunction<String, String, Boolean>() {
            @Override
            public Boolean apply(String t1, String t2) throws Throwable {
                if("用户名20".equals(t1) && "邮箱20".equals(t2)){
                    return true;
                }
                return false;
            }
        });
    }
}
