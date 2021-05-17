package client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * The biggest and only difference between a server and a client in Netty is that
 * different Bootstrap and Channel implementations are used.
 * <p>
 * Bootstrap is similar to ServerBootstrap except that
 * it's for non-server channels such as a client-side or connectionless channel.
 * <p>
 * Instead of NioServerSocketChannel, NioSocketChannel is being used to create a client-side Channel.
 * <p>
 * we do not use childOption() here unlike we did with ServerBootstrap
 * because the client-side SocketChannel does not have a parent.
 * <p>
 * We should call the connect() method instead of the bind() method.
 */
public class NettyClientDemo {
    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "8088"));
    private static final DemoClientHandler handler = new DemoClientHandler();
    private static final Bootstrap bs = new Bootstrap();

    public static void main(String[] args) {
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            bs.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.AUTO_READ, true)
                    .remoteAddress(HOST, PORT)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast(new HttpResponseDecoder())
                                    .addLast(new HttpRequestEncoder())
                                    .addLast(handler);
                        }
                    });

            // Start the client.
            ChannelFuture f = bs.connect().sync();
            // Wait until the connection is closed.
            f.channel().writeAndFlush("1");
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }

//    static void connect() {
//        bs.connect().addListener(new ChannelFutureListener() {
//            @Override
//            public void operationComplete(ChannelFuture future) throws Exception {
//                if (future.cause() != null) {
//                    handler.startTime = -1;
//                    System.out.println("Failed to connect: " + future.cause());
//                }
//            }
//        });
//    }
}
