## 业务逻辑

### 心跳检测

1. client配置IdleStateHandler空闲检测处理器
2. clientHandler实现userEventTriggered方法，监听IdleStateEvent事件，发送心跳请求实体
3. server处理心跳请求，响应心跳结果给client
4. client接受心跳响应

### 用户登录认证
1. client   --AuthRequest-->    server
2. server 进行认证和鉴权等业务... 
3. server   --AuthResponse-->   client


### 私聊

1. clientSender --ChatSendToOneRequest-->       server
2. server       --ChatSendResponse-->           clientSender
3. server       --ChatRedirectToUserRequest-->  clientRecipient


### 群聊(原版)

1. client       --ChatSendToAllRequest-->        server
2. server       --ChatRedirectToUserRequest-->   clientRecipient1
3. server       --ChatRedirectToUserRequest-->   clientRecipient2 
4. 重复步骤2和3   --转发给多个客户端....
5. server       --ChatSendResponse-->            clientSender

### 群聊（改版）
#### 加入群
1. client        --AddGroupRequest-->            server
2. server        --ChatRedirectToUserRequest-->  clientRecipient
3. 重复步骤2       --通知组内接收端有新用户加入
4. server        --ChatSendResponse-->           clientSender
#### 发送群聊消息
与加入群步骤相似，消息类型更换为ChatSendToAllRequest