package com.yundan.server.handler.timer;

import com.yundan.server.util.DateUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.Date;
import java.util.List;

public class ResultEncoder extends MessageToByteEncoder<Date> {


    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Date date, ByteBuf byteBuf) throws Exception {
        byteBuf.writeBytes(DateUtil.toYyyyMMdd(date).getBytes());

    }
//    @Override
//    protected void encode(ChannelHandlerContext channelHandlerContext, Date date, List<Object> list) throws Exception {
//        list.add( DateUtil.toYyyyMMdd(date));
//        channelHandlerContext.close();
//    }
}
