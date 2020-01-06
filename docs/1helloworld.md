### 工程地址
https://github.com/andyzhaozhao/SampleJavaReactor 

### RxJava学习笔记
#### 1.概述学习笔记
RxJava是ReactiveX（反应性扩展）的Java VM实现：一个用于通过使用可观察的序列组成异步和基于事件的程序的库。

RxJava是轻量级的，只有一个jar包。RxJava支持被多JVM语言调用。

#### 2.HelloWorld程序
依赖：
```java
<dependency>
    <groupId>io.reactivex.rxjava3</groupId>
    <artifactId>rxjava</artifactId>
    <version>3.0.0-RC7</version>
</dependency>
```
java函数：
```java
import io.reactivex.rxjava3.core.Flowable;

public class RXJava3HelloWorld {
    public static void main(String[] args) {
        Flowable.just("Hello world").subscribe(System.out::println);

        hello("zhao","li");
    }

    public static void hello(String ... args){
        Flowable.fromArray(args).subscribe(s -> System.out.println("Hello " + s + "!"));
    }
}

```