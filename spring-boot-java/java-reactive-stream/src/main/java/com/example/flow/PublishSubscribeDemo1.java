package com.example.flow;

import java.io.IOException;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 * @author feng
 * @description:
 * @date 2024/11/6 21:52
 */
public class PublishSubscribeDemo1 {


    public static void main(String[] args) throws IOException {
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();

        Flow.Subscriber<String> subscriber = new Flow.Subscriber<>() {

            Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                // 订阅成功后开始消费数据 1 条
                System.out.println("subscribe success ...");
                this.subscription = subscription;
                subscription.request(1);
            }

            @Override
            public void onNext(String item) {
                // 订阅者处理消息的线程是forkJoinPool的线程，简化版的map-reduce，大任务拆成小任务，小任务结果最后聚集的那个
                System.out.println("subscriber thread is " + Thread.currentThread().getName());
                System.out.println("current processed data is " + item);
                if (item.equals("data-8")) {
                    throw new RuntimeException("my exception");
                }
                subscription.request(1);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("error : " + throwable.getMessage());
                subscription.cancel();
            }

            @Override
            public void onComplete() {
                System.out.println("all data processed finished");
            }
        };

        // 订阅关系
        publisher.subscribe(subscriber);
        // 主线程是 main线程
        System.out.println("current thread is " + Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            publisher.submit("data-" + i);
        }

        // 当发布者close时，订阅者才会结束调用completed方法
        publisher.close();

        System.in.read();
    }
}
