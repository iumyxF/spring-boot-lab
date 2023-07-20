# 多线程

## 中断线程

1. interrupt() 方法，作用：中断一个线程。可以配合isInterrupted()方法来判断当前方法是否被中断了
2. join() 方法，作用：让B线程启动在A线程的执行过程中，插入执行。
3. 中断线程还可以在自定义线程中自定义一个变量isRunning，注意需要使用volatile关键字修饰

## 守护线程

设置守护线程调用setDaemon(true)即可。
守护线程的作用：

- 守护线程是为其他线程服务的线程；
- 所有非守护线程都执行完毕后，虚拟机退出；
- 守护线程不能持有需要关闭的资源（如打开文件等）。

## 死锁

死锁的四个必要条件：

1. 互斥条件：一个资源每次只能被一个进程使用。
2. 请求与保持条件：一个进程因请求资源而阻塞时，对已获得的资源保持不放
3. 不剥夺条件:进程已获得的资源，在末使用完之前，不能强行剥夺。
4. 循环等待条件:若干进程之间形成一种头尾相接的循环等待资源关系。

## synchronized

synchronized 解决了多线程竞争问题

## wait 和 notify

synchronized 解决了多线程竞争问题，但是多线程协调运行问题并没有解决
例如：生产者不断生产一个物品，消费者不断消费物品，如果不协调的情况下，消费者不管里面是否有物品都会去不断消费。
正常情况下应该是有物品了，消费者才去消费。
因此，多线程协调运行的原则就是：当条件不满足时，线程进入等待状态；当条件满足时，线程被唤醒，继续执行任务。

- wait() 当一个线程在this.wait()等待时，它就会释放this锁，从而使得其他线程能够获得this锁。
- notify()，在相同的this锁中调用notify()方法，这个方法会唤醒一个正在this锁等待的线程

### 小结

- wait和notify用于多线程协调运行：
- 在synchronized内部可以调用wait()使线程进入等待状态；
- 必须在已获得的锁对象上调用wait()方法；
- 在synchronized内部可以调用notify()或notifyAll()唤醒其他等待线程；
- 必须在已获得的锁对象上调用notify()或notifyAll()方法；
- 已唤醒的线程还需要重新获得锁后才能继续执行。
- 使用时注意判断条件时while还是if，如果多个线程被唤醒争抢1个锁，此时需要使用while

## 常见名词

- AQS(AbstractQueuedSynchronizer)
  -
  AQS是一个用来构建锁和同步器的框架，使用AQS能简单且高效地构造出应用广泛的大量的同步器，比如我们提到的ReentrantLock，Semaphore，其他的诸如ReentrantReadWriteLock，SynchronousQueue，FutureTask等等皆是基于AQS的。当然，我们自己也能利用AQS非常轻松容易地构造出符合我们自己需求的同步器。
- CAS(Compare-and-Swap)
    - CAS 指令需要有 3 个操作数，分别是内存地址 V、旧的预期值 A 和新值 B。当执行操作时，只有当 V 的值等于 A，才将 V 的值更新为B。

## JUC工具类

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