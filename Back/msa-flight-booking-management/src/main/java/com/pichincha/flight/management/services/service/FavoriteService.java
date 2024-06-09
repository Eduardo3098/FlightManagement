package com.pichincha.flight.management.services.service;

import com.pichincha.dragon.ball.services.domain.models.PostFavoriteRequest;
import com.pichincha.flight.management.services.domain.models.DeleteFavoriteRequest;
import com.pichincha.flight.management.services.domain.models.GetFavoritesResponse;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface FavoriteService {

    Mono<ResponseEntity<Void>> postFavorite(Mono<PostFavoriteRequest> favoriteRequest);

    Mono<ResponseEntity<Void>> deleteFavorite(Mono<DeleteFavoriteRequest> favoriteRequest);

    Mono<ResponseEntity<Mono<GetFavoritesResponse>>>getFavorites(Integer userId);
}
