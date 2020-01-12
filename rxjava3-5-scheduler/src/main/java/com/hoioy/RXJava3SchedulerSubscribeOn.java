package com.hoioy;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.io.IOException;

public class RXJava3SchedulerSubscribeOn {
    public static void main(String[] args) throws IOException, InterruptedException {
        subscribe();
    }

    private static void subscribe() {
        Observable.create((ObservableOnSubscribe<String>) emitter -> {
            System.out.println("发送数据" + Thread.currentThread().getName());
            emitter.onNext("数据1");
            emitter.onComplete();
        }).subscribeOn(Schedulers.newThread()).subscribe(s -> {
            System.out.println("订阅者：" + Thread.currentThread().getName());
            System.out.println(s + " in observer");
        });

        //阻塞，防止直接退出
        try {
            System.out.println("main:" + Thread.currentThread());
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void subscribeMuiltiOnSubscribe() {
        Observable.create((ObservableOnSubscribe<String>) emitter -> {
            System.out.println(Thread.currentThread());
            String s = "from 1 created";
            System.out.println(s);
            emitter.onNext(s);
            emitter.onComplete();
        })
                .subscribeOn(Schedulers.computation())
                .map(s -> {
                    System.out.println(Thread.currentThread());
                    System.out.println(s + " in map");
                    return s;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .filter(s -> {
                    System.out.println(Thread.currentThread());
                    System.out.println(s + " in filter");
                    return true;
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.computation())
                .subscribe(s -> {
                    System.out.println(Thread.currentThread());
                    System.out.println(s + " in observer");
                });
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}