package com.example.design.pattern.chain_of_responsibility_pattern.test01;

/**
 * @author iumyxF
 * @description: 基础中间件类
 * @date 2023/8/3 9:17
 */
public abstract class BaseMiddleware {

    private BaseMiddleware next;

    /**
     * 构建中间件对象链
     */
    public static BaseMiddleware link(BaseMiddleware first, BaseMiddleware... chain) {
        BaseMiddleware head = first;
        for (BaseMiddleware nextInChain : chain) {
            head.next = nextInChain;
            head = nextInChain;
        }
        return first;
    }

    /**
     * 子类将通过具体的检查来实现该方法
     *
     * @param email    邮箱
     * @param password 密码
     * @return 结果
     */
    public abstract boolean check(String email, String password);

    /**
     * 在链中的下一个对象上运行检查，或者如果我们在链中的最后一个对象上，则结束遍历。
     */
    protected boolean checkNext(String email, String password) {
        if (next == null) {
            return true;
        }
        return next.check(email, password);
    }
}
