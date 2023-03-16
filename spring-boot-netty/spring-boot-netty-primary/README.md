### demo

    Netty和SpringBoot简单用法

### channel包

    实现自定义channel

### http

    客户端通过http获取Netty服务端状态

### protostuff

    使用protostuff实现Java对象的传输

### udp 

    使用udp协议完成消息传输


### heartbeat
    
    心跳检测


### other

#### server
1. ServerBootstrap中childHandler和handler的区别
- handler是用来监听ServerBootstrap的动作，比如端口绑定和接受新连接
- childHandler是用来监听已经连接的客户端的Channel的动作和状态，比如读写数据和异常处理
- handler是在ServerBootstrap初始化时执行，childHandler是在客户端连接完成后执行
- client客户端中只有handler，Bootstrap中的handler是用来监听Bootstrap的动作，比如连接服务器和关闭通道

