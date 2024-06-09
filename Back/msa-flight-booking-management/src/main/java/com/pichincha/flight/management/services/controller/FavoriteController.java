package com.pichincha.flight.management.services.controller;

import com.pichincha.dragon.ball.services.domain.models.PostFavoriteRequest;
import com.pichincha.flight.management.services.domain.models.DeleteFavoriteRequest;
import com.pichincha.flight.management.services.domain.models.GetFavoritesResponse;
import com.pichincha.flight.management.services.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class FavoriteController {
    private final FavoriteService favoriteService;

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/favorites-management",
            produces = { "application/json", "application/json;charset=UTF-8"},
            consumes = { "application/json"})
    public Mono<ResponseEntity<Void>> postFavorite(@RequestBody Mono<PostFavoriteRequest> favoriteRequest) {
        return favoriteService.postFavorite(favoriteRequest);
    }

    @RequestMapping(            method = RequestMethod.DELETE,
            value = "/favorites-management",
            produces = { "application/json", "application/json;charset=UTF-8"},
            consumes = { "application/json"})
    public Mono<ResponseEntity<Void>> deleteFavorite(@RequestBody Mono<DeleteFavoriteRequest> favoriteRequest) {
        return favoriteService.deleteFavorite(favoriteRequest);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/favorites-management/{id}",
            produces = { "application/json", "application/json;charset=UTF-8"})
    public Mono<ResponseEntity<Mono<GetFavoritesResponse>>> getFavorites(@PathVariable(value = "id") Integer id) {
        return favoriteService.getFavorites(id);
    }
}
