
package com.hoioy;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.io.IOException;

public class RXJava3SchedulerObserveOn {
    public static void main(String[] args) throws IOException, InterruptedException {
        Observable.just("RxJava")
                .observeOn(Schedulers.newThread())
                .map(s -> {
                    System.out.println("map1：" + Thread.currentThread().getName());
                    return s + "-map1";
                })
                .map(s -> {
                    System.out.println("map2：" + Thread.currentThread().getName());
                    return s + "-map2";
                })
                .observeOn(Schedulers.newThread())
                .subscribe(s -> {
                    System.out.println("订阅者：" + Thread.currentThread().getName());
                });

        //阻塞，防止直接执行完毕退出
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
