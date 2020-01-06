### 工程地址
https://github.com/andyzhaozhao/SampleJavaReactor 

### 使用create方法来创建Observable被观察者
除了just和from创建被观察者外，RxJava还支持其他的创建操作符（Creating ），如create()方法。  
create方法实在在subscribe（）方法中通过emitter发送事件数据。
```java
public static Observable createObservableByCreate() {
    return Observable.create(new ObservableOnSubscribe<String>() {
        @Override
        public void subscribe(@NonNull ObservableEmitter<String> observableEmitter) throws Throwable {
        System.out.println("发送：：" + "hello");
            observableEmitter.onNext("hello");
            System.out.println("发送：：" + "world");
            observableEmitter.onNext("world");
            System.out.println("发送：：" + "onComplete");
            observableEmitter.onComplete();
        }
    });
}
```

### 运行
```java
 public static void main(String[] args) {
        subscribe();
    }

    public static void subscribe() {
        createObservableByCreate().subscribe(new Observer() {
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
```
