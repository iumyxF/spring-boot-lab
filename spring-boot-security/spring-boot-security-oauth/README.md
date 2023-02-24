OAuth2.0流程：
三个对象:用户user,本地服务(local-server),第三方服务(Wechat,QQ,ali..)
两个关键信息:Authorization Code(授权码),Access Token(令牌)
以接入QQ登录注册为例：
Step1：申请接入，获取appid和apikey；
Step2：本地服务的页面放置QQ登录按钮，用户点击触发申请Authorization Code；（这里是get请求）
Step3：用户登录授权认证后，QQ服务器响应Authorization Code给本地服务。
Step4：本地服务接受Authorization Code，向QQ服务器申请获取Access Token；（这里是post请求）
Step5：通过Access Token获取用户的OpenID；
Step6：调用OpenAPI，来请求访问或修改用户授权的资源。