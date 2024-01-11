package com.example.reactor.principle;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.function.Function;

/**
 * @author iumyxF
 * @description:
 * @date 2024/1/10 16:56
 *//**/
public class OperatorPublisherTest {
    /*
    增强 MyPublisher 和 MySubscriber 之间的操作
    引入操作类对象

    MySubscription：订阅信息

    AbstractPublisher：抽象发布者
    SourcePublisher：初始数据发布者
    OperatorPublisher：中间操作发布者

    OperatorSubscriptionSubscriber：中间操作订阅者
    MySubscriber：订阅者

    ------------------------

    OperatorPublisher（操作者）：本身是一个 Publisher ，可以通过 source 和 parentPublisher 变量将所有的 Publisher 组织成为一个 Publisher 单向链表，
                    注意的是OperatorPublisher还包含一个函数式接口Function，这个是真正处理数据的地方。
    OperatorSubscriptionSubscriber（新的操作类型的 Subscriber）：当 Publisher 链表被订阅的时候启动遍历 Publisher ，
                    在遍历的过程中也形成了一个 Subscriber 的链表，链表最后一个 Subscriber 为真正的订阅者。
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
     * 抽象类，用于引入操作类的 Publisher
     */
    static abstract class AbstractPublisher implements Publisher {

        public OperatorPublisher operator(Function function, String name) {
            return new OperatorPublisher(this, function, name);
        }
    }

    /**
     * SourcePublisher 用于获取最初始的数据
     */
    static class SourcePublisher extends AbstractPublisher {

        private String name;

        private String data;

        public SourcePublisher(String name) {
            this.name = name;
            println("生成 SourcePublisher ");
        }

        @Override
        public void subscribe(Subscriber subscriber) {
            println("SourcePublisher#subscribe(Subscriber), 生成 MySubscription 对象");
            subscriber.onSubscribe(new MySubscription(data, subscriber));
        }

        public static AbstractPublisher just(String a) {
            SourcePublisher myPublisher = new SourcePublisher("数据源Publisher");
            myPublisher.data = a;
            return myPublisher;
        }

        void println(String context) {
            System.out.println(name + ": " + context);
        }
    }

    static class OperatorPublisher extends AbstractPublisher {

        private String name;

        private Publisher source;

        private OperatorPublisher prePublisher;

        Function mapper;

        public OperatorPublisher(Publisher prePublisher, Function function, String name) {
            this.name = name;
            this.source = prePublisher;
            if (prePublisher instanceof OperatorPublisher) {
                this.prePublisher = (OperatorPublisher) prePublisher;
            }
            mapper = function;
            print("生成 OperatorPublisher 对象");
        }

        @Override
        public void subscribe(Subscriber s) {
            print("订阅：OperatorPublisher#subscribe(Subscriber)");
            OperatorPublisher currentPublisher = this;
            Subscriber currentSubscriber = s;
            while (true) {
                currentSubscriber = currentPublisher.subscribeOrReturn(currentSubscriber);
                OperatorPublisher nextPublisher = currentPublisher.prePublisher;
                if (nextPublisher == null) {
                    currentPublisher.source.subscribe(currentSubscriber);
                    return;
                }
                currentPublisher = nextPublisher;
            }
        }

        public OperatorSubscriptionSubscriber subscribeOrReturn(Subscriber s) {
            print("OperatorPublisher#subscribeOrReturn(Subscriber), 生成 OperatorSubscriptionSubscriber 对象");
            return new OperatorSubscriptionSubscriber(mapper, s, name);
        }

        public void print(String text) {
            System.out.println(name + ": " + text);
        }
    }

    static class OperatorSubscriptionSubscriber implements Subscriber, Subscription {

        private String name;

        private Subscriber preSubscriber;

        private Function function;

        private Subscription preSubscription;

        public OperatorSubscriptionSubscriber(Function f, Subscriber preSubscriber, String name) {
            this.function = f;
            this.preSubscriber = preSubscriber;
            this.name = name + "_OperatorSubscriptionSubscriber";
        }

        private boolean isCanceled;

        @Override
        public void request(long l) {
            println("OperatorSubscriptionSubscriber#request(long)");
            preSubscription.request(l);
        }

        @Override
        public void onSubscribe(Subscription preSubscription) {
            println("OperatorSubscriptionSubscriber#onSubscribe(Subscription)");
            this.preSubscription = preSubscription;
            preSubscriber.onSubscribe(this);
        }

        @Override
        public void onNext(Object o) {
            println("OperatorSubscriptionSubscriber#onNext(Object)");
            Object apply = function.apply(o);
            println("处理后的值为：" + apply);
            preSubscriber.onNext(apply);
        }

        @Override
        public void onError(Throwable t) {
            preSubscriber.onError(t);
        }

        @Override
        public void onComplete() {
            preSubscriber.onComplete();
        }

        @Override
        public void cancel() {
            isCanceled = true;
        }

        void println(String context) {
            System.out.println(name + ": " + context);
        }
    }

    static class MySubscriber implements Subscriber {
        private String name = "最终订阅者MySubscriber：";

        @Override
        public void onSubscribe(Subscription subscription) {
            println("MySubscriber#onSubscribe(Subscription)");
            subscription.request(1L);
        }

        @Override
        public void onNext(Object o) {
            println("最终订阅者MySubscriber: MySubscriber onNext");
            println("最终订阅者MySubscriber：结果输出 " + o);

        }

        @Override
        public void onError(Throwable throwable) {
            println("error");
        }

        @Override
        public void onComplete() {
            println("MySubscriber#onComplete()");
        }

        void println(String context) {
            System.out.println(name + context);
        }
    }

    public static void main(String[] args) {
        SourcePublisher.just("MyPublisher1")// 生成 SourcePublisher
                .operator(e -> e + " operator1 ", "操作对象1")// 生成 OperatorPublisher
                .operator(e -> e + " operator2 ", "操作对象2") // 生成 OperatorPublisher
                .subscribe(new MySubscriber()); // 生成了多个嵌套的  Subscriber 回到 SourcePublisher 进行 onSubscribe 调用链
    }

}
