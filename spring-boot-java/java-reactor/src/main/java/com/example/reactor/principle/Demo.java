package com.example.reactor.principle;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * @author iumyxF
 * @description:
 * @date 2024/1/10 16:56
 */
public class Demo {
    /*
    响应式编程
    核心思想三个接口:Subscriber（订阅者）、Subscription（订阅）、Publisher（发布者）

    首先调用 Publisher#subscribe(Subscriber)方法，传入了一个 Subscriber。
    然后 Subscriber#onSubscribe(Subscription)，传入了一个 Subscription。
    然后 Subscription#request(long) 会被触发。
    然后 Subscriber#onNext(T) 会被触发。
     */

    /**
     * 订阅
     */
    static class MySubscription implements Subscription {

        private String data;

        private Subscriber subscriber;

        private boolean isCanceled;

        public MySubscription(String data, Subscriber subscriber) {
            this.data = data;
            this.subscriber = subscriber;
        }

        @Override
        public void request(long l) {
            if (!isCanceled) {
                try {
                    subscriber.onNext(data);
                    subscriber.onComplete();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        }

        @Override
        public void cancel() {
            isCanceled = true;
        }
    }

    /**
     * 发布者
     */
    static class MyPublisher implements Publisher {

        private String data;

        @Override
        public void subscribe(Subscriber subscriber) {
            subscriber.onSubscribe(new MySubscription(data, subscriber));
        }

        public static Publisher createPublisher(String data) {
            MyPublisher myPublisher = new MyPublisher();
            myPublisher.data = data;
            return myPublisher;
        }
    }

    /**
     * 订阅者
     */
    static class MySubscriber implements Subscriber {

        @Override
        public void onSubscribe(Subscription subscription) {
            subscription.request(1L);
        }

        @Override
        public void onNext(Object o) {
            System.out.println("onNext : " + o);
        }

        @Override
        public void onError(Throwable throwable) {
            System.out.println("error");
        }

        @Override
        public void onComplete() {
            System.out.println("complete");
        }
    }

    public static void main(String[] args) {
        MyPublisher.createPublisher("hello reactor~")
                .subscribe(new MySubscriber());
    }
}
