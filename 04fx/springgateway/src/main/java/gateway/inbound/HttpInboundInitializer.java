package gateway.inbound;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class HttpInboundInitializer extends ChannelInitializer<SocketChannel> {

    private List<String> backendServers;

    @Override
    public void initChannel(SocketChannel ch) {
        System.out.println("init channel handler");
        ChannelPipeline p = ch.pipeline();
        p.addLast(new HttpServerCodec());
        //p.addLast(new HttpServerExpectContinueHandler());
        p.addLast(new HttpObjectAggregator(1024 * 1024));
        p.addLast(getHttpInboundHandler());
    }

    @Bean
    HttpInboundHandler getHttpInboundHandler() {
        return new HttpInboundHandler(this.backendServers);
    }
}
