package com.example.file;

import com.example.netty.file.client.NettyClient;
import com.example.netty.file.domain.FileTransferProtocol;
import com.example.netty.file.util.MsgUtil;
import io.netty.channel.ChannelFuture;

import java.io.File;

/**
 * @description: 客户端文件上传测试
 * @Date 2023/3/11 14:02
 * @Author fzy
 */
public class NettyFileClientTest {
    public static void main(String[] args) {

        //启动客户端
        ChannelFuture channelFuture = new NettyClient().connect("127.0.0.1", 7397);

        //文件信息{文件大于1024kb方便测试断点续传}
        File file = new File("F:\\测试数据\\files\\app-mixer");
        FileTransferProtocol fileTransferProtocol = MsgUtil.buildRequestTransferFile(file.getAbsolutePath(), file.getName(), file.length());
        //发送信息；请求传输文件
        channelFuture.channel().writeAndFlush(fileTransferProtocol);
    }

}
