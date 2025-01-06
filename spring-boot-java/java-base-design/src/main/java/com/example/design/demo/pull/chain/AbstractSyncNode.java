package com.example.design.demo.pull.chain;

import com.example.design.demo.pull.commons.SyncPersonInfoContext;

/**
 * @author fzy
 * @description: 抽象 同步操作节点
 * @date 6/1/2025 下午3:39
 */
public abstract class AbstractSyncNode {

    protected AbstractSyncNode nextNode;

    public void setNextNode(AbstractSyncNode node) {
        this.nextNode = node;
    }

    public abstract void handle(SyncPersonInfoContext context);
}
