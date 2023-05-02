# Spring-Cache 注解
1. @EnableCache
   1. 注释触发后置处理器, 检查每一个Spring bean 的 public 方法是否存在缓存注解。如果找到这样的一个注释, 自动创建一个代理拦截方法调用和处理相应的缓存行为。
2. @Cacheable
   1. 将方法的结果缓存，必须要指定一个 cacheName（缓存空间）
   2. 自定义cache key
   3. 同步缓存 sync=true
   4. 条件缓存 condition（condition表达式为true时缓存），unless和condition相反
   5. Spring EL
3. @CachePut
   1. 这个注解和 @Cacheable 有点类似，都会将结果缓存，但是标记 @CachePut 的方法每次都会执行，目的在于更新缓存，所以两个注解的使用场景完全不同。@Cacheable 支持的所有配置选项，同样适用于@CachePut
   2. 不能在同一个方法上标记@CachePut和@Cacheable
4. @CacheEvict
   1. 移除缓存
   2. 声明 allEntries=true移除该CacheName下所有缓存
   3. 声明 beforeInvocation=true 在方法执行之前清除缓存，无论方法执行是否成功
5. @Caching
   1. 可以让你在一个方法上嵌套多个相同的Cache 注解（@Cacheable, @CachePut, @CacheEvict），分别指定不同的条件

# 个人使用总结
- 增
  - 对于列表查询，使用@Cacheable并且制定value(cacheNames)即可
    - 对于并发环境下的列表查询可以使用sync=true，防止同一个数据同时缓存多个
  - 对于单个查询，使用@Cacheable缓存数据
    - 使用unless判断非空缓存
    - 使用key=#id 使用主键作为redis key
- 删
  - 删除缓存
  - 通过#root.args[0]获取删除的id
- 改
  - 删除缓存
  - 通过#root.args[0]获取删除的id
- 增
  - 不用操作

一些使用连接:
1. https://www.jianshu.com/p/931484bb3fdc
2. https://juejin.cn/post/7067090649245286408#heading-10
