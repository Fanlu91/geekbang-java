package server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class BackendServer {
    // 后台服务端口
    public static final int BACKEND_PORT = 8088;
    // 运行标志位
    public static final AtomicBoolean RunningFlag = new AtomicBoolean(true);

    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        final ServerSocket serverSocket = new ServerSocket(BACKEND_PORT);
        while (RunningFlag.get()) {
            final Socket socket = serverSocket.accept();
            executorService.execute(() -> service(socket));
        }
        // 循环退出之后关闭服务
        serverSocket.close();
        executorService.shutdown();
    }

    private static void service(Socket socket) {

        try {
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            InputStream inputStream = socket.getInputStream();
            byte[] buffer = new byte[8 * 1024];
            // Reads up to len bytes of data from the input stream into an array of bytes.
            // An attempt is made to read as many as len bytes, but a smaller number may be read.
            // The number of bytes actually read is returned as an integer.
            int len = inputStream.read(buffer);
            byteArray.write(buffer, 0, len);

            String inputContent = byteArray.toString();
            System.out.println("-----------BackendServer收到请求:----------------\n" + inputContent);
            byteArray.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            String body = "hello \r\n";
            printWriter.println("Content-Length:" + body.getBytes().length);
            printWriter.println("");
            printWriter.write(body);
            printWriter.println("");
            printWriter.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
