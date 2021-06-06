package com.fanlu.springbootstarter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Properties;

@Getter
@Setter
@ConfigurationProperties(prefix = "fanlu")
public final class PropertiesConfiguration {
    private Properties properties = new Properties();
}
