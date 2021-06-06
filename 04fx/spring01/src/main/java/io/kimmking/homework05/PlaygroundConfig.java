package io.kimmking.homework05;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlaygroundConfig {

    @Bean
    public Playground getPlayground() {
        return new Playground();
    }
}
