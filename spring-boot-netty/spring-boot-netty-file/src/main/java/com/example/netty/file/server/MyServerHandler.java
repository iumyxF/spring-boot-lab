package com.example.netty.file.server;

import com.alibaba.fastjson.JSON;
import com.example.netty.file.domain.*;
import com.example.netty.file.util.CacheUtil;
import com.example.netty.file.util.FileUtil;
import com.example.netty.file.util.MsgUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description:
 * @Date 2023/3/11 11:01
 * @Author fzy
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        SocketChannel channel = (SocketChannel) ctx.channel();
        System.out.println("链接报告开始");
        System.out.println("链接报告信息：有一客户端链接到本服务端。channelId：" + channel.id());
        System.out.println("链接报告IP:" + channel.localAddress().getHostString());
        System.out.println("链接报告Port:" + channel.localAddress().getPort());
        System.out.println("链接报告完毕");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("客户端断开链接" + ctx.channel().localAddress().toString());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws IOException {
        //数据格式验证
        if (!(msg instanceof FileTransferProtocol)) {
            return;
        }
        FileTransferProtocol fileTransferProtocol = (FileTransferProtocol) msg;
        //0传输文件'请求'、1文件传输'指令'、2文件传输'数据'
        switch (fileTransferProtocol.getTransferType()) {
            case 0:
                //处理传输文件'请求'
                FileDescInfo fileDescInfo = (FileDescInfo) fileTransferProtocol.getTransferObj();
                //断点续传信息，实际应用中需要将断点续传信息保存到数据库中
                FileBurstInstruct fileBurstInstructOld = CacheUtil.burstDataMap.get(fileDescInfo.getFileName());
                if (null != fileBurstInstructOld) {
                    if (fileBurstInstructOld.getStatus() == Constants.FileStatus.COMPLETE) {
                        CacheUtil.burstDataMap.remove(fileDescInfo.getFileName());
                    }
                    //传输完成删除断点信息
                    System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " 接收客户端传输文件请求[断点续传]。" + JSON.toJSONString(fileBurstInstructOld));
                    ctx.writeAndFlush(MsgUtil.buildTransferInstruct(fileBurstInstructOld));
                    return;
                }
                //发送信息
                FileTransferProtocol sendFileTransferProtocol = MsgUtil.buildTransferInstruct(Constants.FileStatus.BEGIN, fileDescInfo.getFileUrl(), 0);
                ctx.writeAndFlush(sendFileTransferProtocol);
                System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " 接收客户端传输文件请求。" + JSON.toJSONString(fileDescInfo));
                break;
            case 2:
                //处理文件传输'数据'
                FileBurstData fileBurstData = (FileBurstData) fileTransferProtocol.getTransferObj();
                FileBurstInstruct fileBurstInstruct = FileUtil.writeFile("F://测试数据//receive//", fileBurstData);
                //保存断点续传信息
                CacheUtil.burstDataMap.put(fileBurstData.getFileName(), fileBurstInstruct);
                ctx.writeAndFlush(MsgUtil.buildTransferInstruct(fileBurstInstruct));
                System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " 接收客户端传输文件数据。" + JSON.toJSONString(fileBurstData));
                //传输完成删除断点信息
                if (fileBurstInstruct.getStatus() == Constants.FileStatus.COMPLETE) {
                    CacheUtil.burstDataMap.remove(fileBurstData.getFileName());
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
        System.out.println("异常信息：\r\n" + cause.getMessage());
    }
}
