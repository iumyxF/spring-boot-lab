# 整合onlyOffice实现在线编辑word、excel等文档

1.onlyOffice官方地址 https://api.onlyoffice.com/editors/basic

# 整合步骤

1. docker安装onlyOffice的文档服务器

## 安装onlyOffice

1. docker run -i -t -d -p 9100:80 --restart=always \
   自定义一个secret，后面token验证使用

```shell
sudo docker run -i -t -d -p 9100:80 --restart=always \
    -v /opt/onlyoffice/app/onlyoffice/DocumentServer/logs:/var/log/onlyoffice  \
    -v /opt/onlyoffice/app/onlyoffice/DocumentServer/data:/var/www/onlyoffice/Data  \
    -v /opt/onlyoffice/app/onlyoffice/DocumentServer/lib:/var/lib/onlyoffice \
    -v /opt/onlyoffice/app/onlyoffice/DocumentServer/db:/var/lib/postgresql -e JWT_SECRET=test onlyoffice/documentserver-de
```

2. 确保能通过网址查看config文件:http://{ip:port}/web-apps/apps/api/documents/api.js
3. OnlyOffice 打开文档时提示下载失败 进入容器内部修改 /etc/onlyoffice/documentserver/default.json
   安装vim

```shell
sudo apt update
sudo apt install vim
```

vim 查询字符串 :/${str}

"request-filtering-agent" : {
"allowPrivateIPAddress": true,
"allowMetaIPAddress": true
},

## 顺带回顾远程开发配置

1. 找到IDEA-Tools-Deployment-Configuration 配置SFTP 填写连接地址和修改UTF-8F编码，Mappings 配置文件存放路径
2. 配置自动同步文件修改：IDEA-Tools-Deployment 的 Automatic Upload勾选，配置自动删除找到Options，勾选"Delete remote files
   when local are deleted"
3. 配置远程调试，找到启动配置，Edit Configurations,添加Remote JVM
   Debug，填入Host，即可获得一串命令:[-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005]

```shell
mvn clean package
普通启动: java -jar /xxx/xxx/xxx.jar --spring.profiles.active=prod
断点调试启动: java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -jar /xxx/xxx/xxx.jar --spring.profiles.active=prod
```

注意maven需要添加:

```xml

<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
</plugin>
```