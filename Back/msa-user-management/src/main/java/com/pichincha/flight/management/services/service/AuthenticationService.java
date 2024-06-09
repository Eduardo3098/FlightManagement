package com.pichincha.flight.management.services.service.impl;

import com.pichincha.flight.management.services.domain.models.PostLoginRequest;
import com.pichincha.flight.management.services.domain.models.PostLoginResponse;
import com.pichincha.flight.management.services.domain.models.PostLogoutRequest;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface AuthenticationService {
    Mono<ResponseEntity<PostLoginResponse>> postLogIn(Mono<PostLoginRequest> loginRequest);
    Mono<Void> postLogOut(Mono<PostLogoutRequest> logOutRequest);
}
