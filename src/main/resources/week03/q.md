```less

为什么init 两次？

### gateway log
NIOGateway 3.0.0 starting...
NIOGateway 3.0.0 started at http://localhost:8888 for server:HttpInboundServer(port=8888, backendServers=[http://localhost:8088])
log4j:WARN No appenders could be found for logger (io.netty.util.internal.logging.InternalLoggerFactory).
log4j:WARN Please initialize the log4j system properly.
log4j:WARN See http://logging.apache.org/log4j/1.2/faq.html#noconfig for more info.
开启netty http服务器，监听地址和端口为 http://127.0.0.1:8888/
init channel handler
init inbound handler
out bound handler
submit request
proxy fetch url http://localhost:8088/


### backend log
-----------BackendServer收到请求:----------------
GET / HTTP/1.1
Connection: Keep-Alive
fanlu: 
Host: localhost:8088
User-Agent: Apache-HttpAsyncClient/4.1.4 (Java/1.8.0_232)
```

chrome 正常访问
```less

init channel handler
init channel handler
init inbound handler
out bound handler
init inbound handler
out bound handler
submit request
proxy fetch url http://localhost:8088/
submit request
proxy fetch url http://localhost:8088/favicon.ico
```

chrome 匿名访问
```less
init channel handler
init inbound handler
out bound handler
submit request
proxy fetch url http://localhost:8088/
submit request
proxy fetch url http://localhost:8088/favicon.ico

```