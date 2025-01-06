package com.example.design.demo.pull.chain;

import com.example.design.demo.pull.commons.SyncPersonInfoContext;

/**
 * @author fzy
 * @description:
 * @date 6/1/2025 下午3:43
 */
public class CheckConfigNode extends AbstractSyncNode {

    @Override
    public void handle(SyncPersonInfoContext context) {
        if (context.getType() == null) {
            System.out.println("同步方式不能为空");
            return;
        }
        if (context.getType() == 1) {
            System.out.println("检查 钉钉 配置参数 ...");
        } else {
            System.out.println("检查 微信 配置参数 ...");
        }
        if (nextNode != null) {
            nextNode.handle(context);
        }
    }
}
