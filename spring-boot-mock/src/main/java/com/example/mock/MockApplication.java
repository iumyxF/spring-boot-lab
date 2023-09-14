package com.example.mock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description: 案例来源：https://mp.weixin.qq.com/s?__biz=MzIzOTU0NTQ0MA==&mid=2247506942&idx=1&sn=7c38f14039ce63a0abb09764350851b9&chksm=e92ae6f1de5d6fe73ad6a4118d9c9ec8a2caa6dc69b3ce2ba718955eb37c3f338e51ea165495&scene=178&cur_album_id=1538305828238262273#rd
 * 基于链路思想的SpringBoot单元测试快速写法
 * 步骤：
 * 1、快速写法的入口是controller层方法
 * 2、设计测试用例的输入与预期输出。需要覆盖代码中的if-else分支
 * 3、确定链路上的全部Mock点。Mock点的判断依据是链路上该环节是否依赖第三方服务。强烈建议在设计前画出大概的功能流程图(如”用户买猪“图)，这可以大大提高确定Mock点的速度和准确性。
 * 4、收集Mock点的模拟返回数据。Mock数据需要考虑多个因素：
 * 4.1、是否与api层对应方法的期望返回值匹配: 不能把从猪厂返回的Mock数据用牛肉替代
 * 4.2、是否与模拟输入数据匹配：用户需要1斤猪肉，不能返回5斤猪肉的数据
 * 4.3、是否与api层的所有分支匹配：部分api层会对返回值进行响应码(2xx || 3xx || 4xx)校验，这类场景便需要构造不同响应码的Mock数据
 * @Date 2023/2/11 10:31
 * @author iumyxF
 */
@SpringBootApplication
public class MockApplication {
    public static void main(String[] args) {
        SpringApplication.run(MockApplication.class, args);
    }
}
