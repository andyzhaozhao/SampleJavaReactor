package com.hoioy;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class RXJava3ErrorCatch {
    public static void main(String[] args) throws IOException {
//        subscribe();
        String res = doGet("https://www.baidu.com");
        System.out.println(res);
    }

    /**
     * 以get方式调用第三方接口
     * @param url
     * @return
     */
    public static String doGet(String url) throws IOException {
        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(url);
        HttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            //返回json格式
            String res = EntityUtils.toString(response.getEntity());
            return res;
        }
        return null;
    }


    private static void subscribe() {
        Observable observableByCreate = createObservableByCreate();

        observableByCreate.doOnError(error -> {
            System.out.println(error);
        });

        observableByCreate.subscribe(new Observer() {
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
        Observable.interval()

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
