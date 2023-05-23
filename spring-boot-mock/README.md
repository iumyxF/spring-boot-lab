# JUnit5

junit5 就是 org.junit.jupiter
参考链接:https://pdai.tech/md/develop/ut/dev-ut-x-junit5.html

## 注解

### 声明周期注解

- @Test 表示方法是一种测试方法。 与JUnit 4的@Test注解不同，此注释不会声明任何属性。
- @BeforeEach 表示方法在每个测试方法运行前都会运行
- @AfterEach 表示方法在每个测试方法运行之后都会运行
- @BeforeAll 表示方法在所有测试方法之前运行
- @AfterAll 表示方法在所有测试方法之后运行
- @Disabled 用于禁用测试类或测试方法
- @Tag 用于在类或方法级别声明用于过滤测试的标记

### 其他注解

- @Nested 表示带注解的类是嵌套的非静态测试类，@BeforeAll和 @AfterAll方法不能直接在@Nested测试类中使用，除非修改测试实例生命周期。
- @ParameterizedTest 表示方法是参数化测试
- @RepeatedTest 表示方法是重复测试模板@TestFactory 表示方法是动态测试的测试工程
- @DisplayName 为测试类或者测试方法自定义一个名称
- @ExtendWith 用于注册自定义扩展，该注解可以继承
- @FixMethodOrder(MethodSorters.NAME_ASCENDING)
  ，控制测试类中方法执行的顺序，这种测试方式将按方法名称的进行排序，由于是按字符的字典顺序，所以以这种方式指定执行顺序会始终保持一致；不过这种方式需要对测试方法有一定的命名规则，如
  测试方法均以testNNN开头（NNN表示测试方法序列号 001-999）

## 断言测试

- assertEquals 断言预期值和实际值相等
- assertAll 分组断言,执行其中包含的所有断言
- assertArrayEquals 断言预期数组和实际数组相等
- assertFalse 断言条件为假
- assertNotNull 断言不为空
- assertSame 断言两个对象相等
- assertTimeout 断言超时
- fail 使单元测试失败

# Mock

mock测试就是在测试过程中，对那些不容易构建的对象用一个虚拟对象来代替测试的方法就叫mock测试。
参考链接:https://pdai.tech/md/develop/ut/dev-ut-x-mockito.html

## 基本使用

1. mock 对象的方法的返回值默认都是返回类型的默认值
2. 给mock出来的对象赋值不能通过set方法，这是无效的。需要通过打桩thenReturn的方式制定返回值

### Junit4和Junit5 在配合Mock使用中出现的区别

1. Junit4使用RunWith注解，如果是SpringBoot下的测试Junit5直接使用@SpringBootTest代替即可，如果是普通测试，则Junit5使用@ExtendWith来代替。

### 参数化匹配
link:https://pdai.tech/md/develop/ut/dev-ut-x-mockito.html#%E6%B5%8B%E8%AF%95-%E4%BD%BF%E7%94%A8mock%E6%96%B9%E6%B3%95

## 注解

- @InjectMocks：创建一个实例，简单的说是这个Mock可以调用真实代码的方法，其余用@Mock（或@Spy）注解创建的mock将被注入到用该实例中
- @Mock：对函数的调用均执行mock（即虚假函数），不执行真正部分。
- @Spy： 对函数的调用均执行真正部分。