package com.example.design.pattern.chain_of_responsibility_pattern.test01;

/**
 * @author iumyxF
 * @description: 检查是否有太多登录请求失败。
 * @date 2023/8/3 9:20
 */
public class ThrottlingMiddleware extends BaseMiddleware {

    private static final int ONE_MIN_MILLIS = 60000;
    private final int requestPerMinute;
    private int request;
    private long currentTime;

    public ThrottlingMiddleware(int requestPerMinute) {
        this.requestPerMinute = requestPerMinute;
        this.currentTime = System.currentTimeMillis();
    }

    /**
     * 请注意，checkNext() 调用既可以插入该方法的开头，也可以插入该方法的结尾。
     * 这比对所有中间件对象进行简单循环要灵活得多。
     * 例如，链中的一个元素可以在所有其他检查之后运行其检查，从而改变检查顺序。
     */
    public boolean check(String email, String password) {
        System.out.println("ThrottlingMiddleware 认证...");
        if (System.currentTimeMillis() > currentTime + ONE_MIN_MILLIS) {
            request = 0;
            currentTime = System.currentTimeMillis();
        }
        request++;
        if (request > requestPerMinute) {
            System.out.println("请求超过限制!");
            Thread.currentThread().stop();
        }
        return checkNext(email, password);
    }
    /**
     * Thread.currentThread().stop();
     * 补充线程停止知识点：
     * 1.创建一个volatile修饰的标志符号变量来结束run（）方法
     * 2.使用stop()方法，但是不安全，会导致数据不一致问题
     * 3.使用interrupt(),在业务逻辑中使用Thread.isInterrupted()方法判断线程是否被中断了
     */
}
