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

用户点击github的猫猫头
后端controller接受，"redirect:https://github.com/login/oauth/authorize?client_id=fc8f3ccbb826259852fe&redirect_uri=<你github设置的callback地址>"
用户登录成功，点击授权后，触发回调接口，同时URL上附带了code参数
回调接口中根据code向令牌发放的服务器申请令牌（accessToken），然后根据令牌获取用户github的信息，最后重定向到内部页面
参考：https://blog.csdn.net/Lee_01/article/details/103691864?spm=1001.2101.3001.6650.5&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-5-103691864-blog-112911491.pc_relevant_aa2&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-5-103691864-blog-112911491.pc_relevant_aa2&utm_relevant_index=6
