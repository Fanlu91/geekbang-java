package io.netty.example;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * Because we are going to ignore any received data but to send a message as soon as a connection is established,
     * we cannot use the channelRead() method this time.
     * Instead, we should override the channelActive() method.
     *
     * the channelActive() method will be invoked when a connection is established and ready to generate traffic.
     *
     * @param ctx
     */
    @Override
    public void channelActive(final ChannelHandlerContext ctx) { // (1)
        final ByteBuf time = ctx.alloc().buffer(4); // (2)
        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));

        //  A ChannelFuture represents an I/O operation which has not yet occurred.
        //  It means, any requested operation might not have been performed yet because all operations are asynchronous in Netty.
        final ChannelFuture f = ctx.writeAndFlush(time); // (3)
        // due to its asynchronous, might close the connection even before a message is sent
        // therefore, call the close() method after the ChannelFuture is complete
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) {
                assert f == future;
                ctx.close();
                // close() also might not close the connection immediately,
                // and it returns a ChannelFuture.
            }
        }); // (4)
        // Alternatively, you could simplify the code using a pre-defined listener:
        // f.addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}