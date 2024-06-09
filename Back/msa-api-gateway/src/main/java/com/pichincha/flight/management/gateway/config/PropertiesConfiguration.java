package com.pichincha.flight.management.gateway.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
@Getter
@Setter
@ToString
public class PropertiesConfiguration {

    @Value("${PRIVATE_KEY}")
    String privateKey;
}
