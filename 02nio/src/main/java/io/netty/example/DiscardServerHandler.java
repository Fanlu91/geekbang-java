package io.netty.example;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * Handles a server-side channel.
 *
 * ChannelInboundHandlerAdapter is an implementation of ChannelInboundHandler
 *
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter { // (1)

    // This method is called with the received message, whenever new data is received from a client.
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
//        // Discard the received data silently.
//        ((ByteBuf) msg).release(); // (3)
//    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf in = (ByteBuf) msg;
        try {
            while (in.isReadable()) { // (1)
                System.out.print((char) in.readByte());
                System.out.flush();
            }
            // or
            // System.out.println(in.toString(io.netty.util.CharsetUtil.US_ASCII))
        } finally {
            // Alternatively, you could do in.release() here.
            ReferenceCountUtil.release(msg); // (2)
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}