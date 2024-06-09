package com.pichincha.flight.management.services.service.impl;

import com.pichincha.flight.management.services.domain.models.PostLoginRequest;
import com.pichincha.flight.management.services.domain.models.PostLoginResponse;
import com.pichincha.flight.management.services.domain.models.PostLogoutRequest;
import com.pichincha.flight.management.services.domain.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthenticationServiceImpl implements com.pichincha.flight.management.services.service.impl.AuthenticationService {

    private final AuthRepository authRepository;

    @Override
    public Mono<ResponseEntity<PostLoginResponse>> postLogIn(Mono<PostLoginRequest> loginRequest) {
        log.info("New login request");
        return loginRequest
                .map(postLogInRequest -> {
            String userId = UUID.randomUUID().toString();
            PostLoginResponse response = new PostLoginResponse();
            response.setUserId(userId);
            authRepository.saveSession(postLogInRequest, userId);
            return ResponseEntity.ok().body(response);
        })
                .doOnNext(x -> log.info("User logged"))
                .doOnError(e -> log.error("Error in user login", e));
    }

    @Override
    public Mono<Void> postLogOut(Mono<PostLogoutRequest> logoutRequest) {
        log.info("New logout request");
        return logoutRequest
                .flatMap(postLogoutRequest -> authRepository.deleteSession(postLogoutRequest.getUserId()));
    }
}
