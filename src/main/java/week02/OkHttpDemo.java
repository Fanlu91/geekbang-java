package week02;

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
        String response = example.run("http://localhost:8081");
        System.out.println(response);
    }
}
