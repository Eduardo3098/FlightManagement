package com.pichincha.flight.management.gateway.config;

import com.google.gson.Gson;
import com.pichincha.flight.management.gateway.encryption.Encryption;
import com.pichincha.flight.management.gateway.model.EncryptedModel;
import com.pichincha.flight.management.gateway.model.RsaModel;
import io.netty.handler.codec.http.HttpMethod;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class GatewayConfig {

    private final Encryption encryption;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth", r -> r.path("/api/auth/**")
                        .filters(this::setGatewayFilterSpec)
                        .uri("http://localhost:8081"))
                .build();
    }

    @SneakyThrows
    public GatewayFilterSpec setGatewayFilterSpec(GatewayFilterSpec filterSpec) {
        return filterSpec.modifyRequestBody(EncryptedModel.class, String.class, (exchange, originalRequest) -> {
            if(exchange.getRequest().getMethod().equals(HttpMethod.GET)) {
                return Mono.empty();
            }
            try {
                String decryptRsa;
                String decryptAES;
                if(originalRequest != null) {
                    decryptRsa = encryption.decryptRsa(originalRequest.getEncryptionData());
                    log.info("decryptRsa: {}", decryptRsa);
                    RsaModel rsaModel = new Gson().fromJson(decryptRsa, RsaModel.class);
                    decryptAES = encryption.decryptAES(originalRequest.getContent(), rsaModel.getKey(), rsaModel.getIv());
                    return Mono.just(decryptAES);
                }
                return Mono.empty();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
//                .modifyResponseBody(String.class, String.class, ((exchange, s) -> {
//            String iv = encryption.generateRandom(8);
//            String key = encryption.generateRandom(8);
//            String chipperPayload = "";
//            String rsaEncrypted = "";
//            try {
//                chipperPayload = encryption.encryptAES(s, key, iv);
//                RsaModel rsaModel = new RsaModel();
//                rsaModel.setIv(iv);
//                rsaModel.setKey(key);
//                rsaEncrypted = encryption.encryptRsa(new Gson().toJson(rsaModel));
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//            EncryptedModel encryptedModel = new EncryptedModel();
//            encryptedModel.setContent(chipperPayload);
//            encryptedModel.setEncryptionData(chipperPayload);
//            return Mono.just(s);
//        }));
    }
}
