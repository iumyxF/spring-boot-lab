# 使用流程引擎实现功能

## 场景

当用户使用打车软件，如何计算出预计金额?

代驾费用 = 里程费 + 等候费 + 远途费

## 为什么要用流程引擎

1. 实际上根据业务可能会有其他费用收取(如暴雨、大雪、高温天气增加司机的费用)
2. 根据不同的时间段，起步价会发生变化，或者根据不同城市消费水平，价格也会发生变化

## 规则定义

### 里程费

接单时间

- 0点~6点 3公里内 收费19元 超出后4元/公里
- 6点~24点 5公里内 收费19元 超出后3元/公里

### 等候费

- 等候时间超过10分钟后，1元/1分钟

### 远途费

- 总行程超过12公里 1元/公里
