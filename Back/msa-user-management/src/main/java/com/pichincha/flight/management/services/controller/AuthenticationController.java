package com.pichincha.flight.management.services.controller;

import com.pichincha.flight.management.services.domain.models.PostLoginRequest;
import com.pichincha.flight.management.services.domain.models.PostLoginResponse;
import com.pichincha.flight.management.services.domain.models.PostLogoutRequest;
import com.pichincha.flight.management.services.service.impl.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(("api/auth"))
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/login",
            produces = { "application/json", "application/json;charset=UTF-8"},
            consumes = { "application/json"}
    )
    public Mono<ResponseEntity<PostLoginResponse>> postLogin(@RequestBody Mono<PostLoginRequest> postLoginRequest) {
        return authenticationService.postLogIn(postLoginRequest);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/logout",
            produces = { "application/json", "application/json;charset=UTF-8"},
            consumes = { "application/json"}
    )
    public Mono<Void> postLogout(@RequestBody Mono<PostLogoutRequest> postLogoutRequest) {
        return authenticationService.postLogOut(postLogoutRequest);
    }
}
