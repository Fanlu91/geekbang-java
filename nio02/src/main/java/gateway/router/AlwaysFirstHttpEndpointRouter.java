package gateway.router;

import java.util.List;

public class AlwaysFirstHttpEndpointRouter implements HttpEndpointRouter {
    @Override
    public String route(List<String> urls) {
        return urls.get(0);
    }
}
