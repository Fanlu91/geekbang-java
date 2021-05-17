package client;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * https://square.github.io/okhttp/
 */
public class OkHttpDemo {
    public static OkHttpClient client = new OkHttpClient();

    public String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static void main(String[] args) throws IOException {
        OkHttpDemo example = new OkHttpDemo();
//        String response = example.run("http://127.0.0.1:8088/api/hello");
        String response = example.run("http://127.0.0.1:8888");
//        String response = example.run("http://127.0.0.1:8088");
//        String response = example.run("http://127.0.0.1:8080/1");
        System.out.println(response);
    }
}
