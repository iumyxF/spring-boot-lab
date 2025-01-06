package com.example.design.demo.pull.chain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fzy
 * @description:
 * @date 6/1/2025 下午3:35
 */
public class PersonnelInfoSyncChainConfiguration {

    private List<String> steps;

    private final Map<String, AbstractSyncNode> nodeMap = new HashMap<>(16);

    public PersonnelInfoSyncChainConfiguration(List<AbstractSyncNode> nodeList) {
        nodeMap.put("checkConfigNode", nodeList.stream().filter(n -> n instanceof CheckConfigNode).findFirst().orElse(null));
        nodeMap.put("executeSyncDepartNode", nodeList.stream().filter(n -> n instanceof ExecuteSyncDepartNode).findFirst().orElse(null));
        nodeMap.put("executeSyncUserNode", nodeList.stream().filter(n -> n instanceof ExecuteSyncUserNode).findFirst().orElse(null));
    }

    public AbstractSyncNode syncNodeChain() {
        if (steps == null || steps.isEmpty()) {
            throw new IllegalArgumentException("处理链步骤不能为空");
        }
        AbstractSyncNode first = null;
        AbstractSyncNode next = null;
        for (String step : steps) {
            AbstractSyncNode node = nodeMap.get(step);
            if (node == null) {
                throw new IllegalArgumentException("未找到处理器: " + step);
            }
            if (first == null) {
                first = node;
            }
            if (next != null) {
                next.setNextNode(node);
            }
            next = node;
        }
        next.setNextNode(null);
        return first;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }
}
