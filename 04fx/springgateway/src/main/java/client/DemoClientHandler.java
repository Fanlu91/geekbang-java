package client;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;

import java.net.URI;
import java.net.URISyntaxException;

public class DemoClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws URISyntaxException {

        System.out.println("Connected to: " + ctx.channel().remoteAddress());
        // 发送请求
        URI uri = new URI("hello");
        FullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1,
                HttpMethod.GET,
                uri.toASCIIString());
        request.headers().add(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        request.headers().add(HttpHeaderNames.CONTENT_LENGTH, request.content().readableBytes());
        ctx.writeAndFlush(request);

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("--handler read channel--");
        if (msg instanceof HttpResponse) {
            System.out.println("HttpResponse :");
            HttpResponse response = (HttpResponse) msg;
            System.out.println(response.toString());
        }
        if (msg instanceof HttpContent) {
            System.out.println("HttpContent :");
            HttpContent content = (HttpContent) msg;
            ByteBuf buf = content.content();
            System.out.println(buf.toString(io.netty.util.CharsetUtil.UTF_8));
            buf.release();
        }
        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("--handler read complete--");
        super.channelReadComplete(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
