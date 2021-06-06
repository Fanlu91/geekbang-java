package gateway.router;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
public class AlwaysFirstHttpEndpointRouter implements HttpEndpointRouter {
    @Override
    public String route(List<String> urls) {
        return urls.get(0);
    }
}
