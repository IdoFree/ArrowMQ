package com.yundan.server.handler.timer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class CommandDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        byte[] command = new byte[4];
        if(byteBuf.readableBytes() >4 ){
            byteBuf.readBytes(command,0,4);
            String cmd = new String(command,"utf-8");
            list.add(cmd);
        }



    }
}
