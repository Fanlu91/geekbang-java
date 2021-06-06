package gateway;


import gateway.inbound.HttpInboundServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;

//@ConfigurationProperties(prefix = "gateway")
//@ComponentScan(basePackages = "gateway")
@Component
public class NettyServerApplication {

    public final static String GATEWAY_NAME = "NIOGateway";
    public final static String GATEWAY_VERSION = "3.0.0";

    static String proxyPort = System.getProperty("proxyPort", "8888");
    static String backendServer = System.getProperty("backendServer", "http://localhost:8088");

//    @Autowired
//    HttpInboundServer server;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION + " starting...");

        System.out.println("BeanDefinitionNames() ===>> " + String.join(",", context.getBeanDefinitionNames()));

        int port = Integer.parseInt(proxyPort);
        HttpInboundServer server = (HttpInboundServer) context.getBean("httpInboundServer");
//        HttpInboundServer server = context.getBean(NettyServerApplication.class).server;

        server.setBackendServers(Arrays.asList(backendServer.split(",")));
        server.setPort(port);

        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION + " started at http://localhost:" + port + " for server:" + server.toString());
        try {
            server.run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
