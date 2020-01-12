package com.hoioy;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class RXJava3UseInterval {
    public static void main(String[] args) throws IOException, InterruptedException {
        subscribe();
    }

    private static void subscribe() {
        createObservableByCreate().doOnNext(new Consumer() {
            @Override
            public void accept(Object o) throws Throwable {
                System.out.println("side effect接收到：" + o);
                CloseableHttpClient httpClient = HttpClientBuilder.create().build();
                HttpGet get = new HttpGet("https://www.baidu.com");
                HttpResponse response = httpClient.execute(get);
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    //返回json格式
                    String res = EntityUtils.toString(response.getEntity());
                    System.out.println(res);
                } else {
                    throw new IOException("网络请求错误");
                }
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
        return Observable.interval(2, 1, TimeUnit.SECONDS, Schedulers.trampoline());
    }
}
