package com.example.mock.controller;


import com.example.mock.api.FactoryApi;
import com.example.mock.api.WareHouseApi;
import com.example.mock.dao.PorkStorageMapper;
import com.example.mock.entities.PorkInst;
import com.example.mock.entities.PorkStorage;
import com.example.mock.exception.BaseBusinessException;
import com.example.mock.service.PorkServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.stubbing.Answer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

/**
 * @description: 链路测试从Controller开始
 * @Date 2023/2/11 14:22
 * @Author fzy
 */
@Slf4j
public class PorkControllerTest {

    @InjectMocks
    private PorkController porkController;

    /**
     * 接口类型的链路环节用实现类初始化代替, @Spy需要手动初始化避免initMocks时失败
     * 注：链路上每一环都必须声明，即使测试用例中并没有被显性调用
     * <p>
     * 有个问题，如果service用了@RequiredArgsConstructor 注解，mock怎么注入？
     */
    @InjectMocks
    @Spy
    private PorkServiceImpl porkService = new PorkServiceImpl();

    @Mock
    private PorkStorageMapper porkStorageMapper;

    @Mock
    private FactoryApi factoryApi;

    @Mock
    private WareHouseApi wareHouseApi;

    /**
     * 预置数据可直接作为类变量声明
     */
    private final Map<String, Object> mockParams = new HashMap<String, Object>() {{
        put("user", "system_user");
    }};

    @BeforeEach
    public void setUp() {
        //必要：初始化该类中锁
        MockitoAnnotations.openMocks(this);

        // Mock预置数据并绑定相关方法(适用于有返回值的方法)
        PorkStorage mockStorage = PorkStorage.builder().id(1L).cnt(10L).build();

        // 常见Mock写法一：仅试图Mock返回值
        // 打桩：porkStorageMapper查询的结果返回mockStorage
        when(porkStorageMapper.queryStore()).thenReturn(mockStorage);

        // 常见Mock写法二：不仅试图Mock返回值，还想额外打些日志方便定位
        // wareHouseApi.packagePork接受任何参数，都会执行thenAnswer中的方法
        when(wareHouseApi.packagePork(any(), any()))
                .thenAnswer(ans -> {
                    log.info("发送供求通知给工厂");
                    return PorkInst.builder()
                            .weight(ans.getArgument(0, Long.class))
                            .paramsMap(ans.getArgument(1, Map.class))
                            .build();
                });

        // Mock动作并绑定相关方法(适用于无返回值方法)
        // 当factoryApi.supplyPork执行了就执行invocationOnMock中的方法
        doAnswer((Answer<Void>) invocationOnMock -> {
            log.info("mock factory api success!");
            return null;
        }).when(factoryApi).supplyPork(any());
    }

    @AfterEach
    public void tearDown() {
        // 可以加入Mock数据清理或资源释放
    }

    /**
     * 当传入参数为null时，抛出业务异常
     */
    @Test()
    public void testBuyPorkIfWeightIsNull() {
        Assertions.assertThrows(BaseBusinessException.class, () -> porkController.buyPork(null, mockParams));
    }

    /**
     * 当后台库存不满足需求时，抛出业务异常
     */
    @Test
    public void testBuyPorkIfStorageIsShortage() {
        Assertions.assertThrows(BaseBusinessException.class, () -> {
            porkController.buyPork(20L, mockParams);
        });
    }

    /**
     * 正常购买时返回业务结果
     */
    @Test
    public void testBuyPorkIfResultIsOk() {
        Long expectWeight = 5L;

        ResponseEntity<PorkInst> res = porkController.buyPork(expectWeight, mockParams);
        // 此处第一次校验接口返回状态是否符合预期
        // 校验了库存数量足够时，仓库可以直接执行打包方法，返回猪肉信息。HttpStatus.OK
        Assertions.assertEquals(HttpStatus.OK, res.getStatusCode());

        Long actualWeight = Optional.of(res).map(HttpEntity::getBody).map(PorkInst::getWeight).orElse(-99L);
        // 此处第二次校验接口返回值是否符合预期
        Assertions.assertEquals(expectWeight, actualWeight);
    }

}