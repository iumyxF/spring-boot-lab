package com.example.design.demo.pull;

import com.example.design.demo.pull.chain.*;
import com.example.design.demo.pull.commons.SyncPersonInfoContext;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author fzy
 * @description:
 * @date 6/1/2025 下午4:12
 */
public class MainApplication {

    public static void main(String[] args) {
        ArrayList<AbstractSyncNode> nodes = new ArrayList<>();
        nodes.add(new CheckConfigNode());
        nodes.add(new ExecuteSyncDepartNode());
        nodes.add(new ExecuteSyncUserNode());
        PersonnelInfoSyncChainConfiguration chainConfiguration = new PersonnelInfoSyncChainConfiguration(nodes);
        chainConfiguration.setSteps(Arrays.asList("checkConfigNode", "executeSyncDepartNode", "executeSyncUserNode"));
        AbstractSyncNode chain = chainConfiguration.syncNodeChain();
        SyncPersonInfoContext context = new SyncPersonInfoContext();
        context.setId(1L);
        context.setType(2);
        chain.handle(context);
    }
}
