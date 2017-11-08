package com.yundan.arrowmq.core;

import com.yundan.arrowmq.handler.DriverHandler;
import com.yundan.arrowmq.handler.OutHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;

public class Connector {

    private String host;
    private int port;
    private ChannelFuture f;

    public Connector(String host, int port) {
        this.host = host;
        this.port = port;
    }


    public Channel connect() {
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap(); // (1)
            b.group(workerGroup); // (2)
            b.channel(NioSocketChannel.class); // (3)
            b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new LineBasedFrameDecoder(1024,false,true),new DriverHandler(),new OutHandler());
                }
            });

            // Start the client.
            this.f = b.connect(this.host, this.port).sync(); // (5)

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }

        return new SampleChannel(this.f);
    }



    public static void main(String[] args) throws InterruptedException {
        Connector connector = new Connector("localhost",8080);
        SampleChannel channel = (SampleChannel) connector.connect();
        channel.write("channel","test");
        Thread.sleep(5000);
        channel.close();



    }
}
