# 常见名词

- AQS(AbstractQueuedSynchronizer)
    - AQS是一个用来构建锁和同步器的框架，使用AQS能简单且高效地构造出应用广泛的大量的同步器，比如我们提到的ReentrantLock，Semaphore，其他的诸如ReentrantReadWriteLock，SynchronousQueue，FutureTask等等皆是基于AQS的。当然，我们自己也能利用AQS非常轻松容易地构造出符合我们自己需求的同步器。
- CAS(Compare-and-Swap)
    - CAS 指令需要有 3 个操作数，分别是内存地址 V、旧的预期值 A 和新值 B。当执行操作时，只有当 V 的值等于 A，才将 V 的值更新为B。

# JUC工具类

- Condition：用于线程之间通信
- CountDownLatch：用于一个线程等待其他线程的情景。强调一个线程等多个线程完成某件事情。
- CyclicBarrier：用于一个线程等多个线程完成某件事情。它强调的是n个线程，大家相互等待，只要有一个没完成，所有线程都得等待。
- Semaphore：信号量
- ReentrantLock：创建锁，new ReentrantLock（true）创建一个公平锁，不写则是非公平锁。
    - 非公平锁：当锁处于无线程占有的状态，此时其他线程和在队列中等待的线程都可以抢占该锁。
    - 公平锁：当锁处于无线程占有的状态，在其他线程抢占该锁的时候，都需要先进入队列中等待。
- ReentrantReadWriteLock：读写锁。
- 阻塞队列.
- Callable：Callable接口支持返回执行结果，此时需要调用FutureTask.get()方法实现，此方法会阻塞主线程直到获取结果；当不调用此方法时，主线程不会阻塞！
- 线程池。

## CountDownLatch和CyclicBarrier区别

1. CountDownLatch 一般用于**某个线程A等待若干个其他线程**执行完任务之后，它才执行；
2. CyclicBarrier 一般用于**一组线程互相等待**至某个状态，然后这一组线程再同时执行；
3. CountDownLatch 是不能够重用的，而 CyclicBarrier 是可以重用的。