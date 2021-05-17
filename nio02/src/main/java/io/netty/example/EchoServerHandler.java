package io.netty.example;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


/**
 * Handles a server-side channel.
 *
 * ChannelInboundHandlerAdapter is an implementation of ChannelInboundHandler
 *
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter { // (1)

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ctx.write(msg); // (1)
        // ctx.write(Object) does not make the message written out to the wire.
        // It is buffered internally and then flushed out to the wire by ctx.flush().
        ctx.flush(); // (2)
        // Alternatively, you could call ctx.writeAndFlush(msg) for brevity.
        //we did not release the received message unlike we did in the DISCARD example.
        // It is because Netty releases it for you when it is written out to the wire.
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}