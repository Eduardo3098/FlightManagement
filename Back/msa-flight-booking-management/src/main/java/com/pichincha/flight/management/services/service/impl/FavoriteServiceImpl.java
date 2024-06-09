package com.pichincha.flight.management.services.service.impl;

import com.google.gson.Gson;
import com.pichincha.dragon.ball.services.domain.models.PostFavoriteRequest;
import com.pichincha.flight.management.services.domain.entities.CharactersEntity;
import com.pichincha.flight.management.services.domain.entities.FavoritesEntity;
import com.pichincha.flight.management.services.domain.models.DeleteFavoriteRequest;
import com.pichincha.flight.management.services.domain.models.GetFavoritesResponse;
import com.pichincha.flight.management.services.domain.repository.CharactersRepository;
import com.pichincha.flight.management.services.domain.repository.FavoritesRepository;
import com.pichincha.flight.management.services.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class FavoriteServiceImpl implements FavoriteService {

    private final CharactersRepository charactersRepository;
    private final FavoritesRepository favoritesRepository;

    @Override
    public Mono<ResponseEntity<Void>> postFavorite(Mono<PostFavoriteRequest> favoriteRequest) {
        log.info("Create new favorite");
        return favoriteRequest.flatMap(postFavoriteRequest -> {
            log.debug("Object favoriteRequest Request in Json: [request:{}]", new Gson().toJson(postFavoriteRequest));

            CharactersEntity characters = new CharactersEntity();
            characters.setClientId(postFavoriteRequest.getClientId());

            return charactersRepository
                    .save(characters)
                    .doOnError(e -> log.error("Error saving new character", e))
                    .doOnNext(x -> log.info("Character saved"))
                    .flatMap(charactersEntity -> {
                        FavoritesEntity favoritesEntity = new FavoritesEntity();
                        favoritesEntity.setUserId(postFavoriteRequest.getUserId());
                        favoritesEntity.setCharactersId(charactersEntity.getCharactersId());
                        return favoritesRepository.save(favoritesEntity)
                                .doOnError(e -> log.error("Error saving new favorite", e))
                                .doOnNext(x -> log.info("Favorite saved"))
                                .map(favoritesEntity1 -> ResponseEntity.ok().body(null));
                    });
        });
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteFavorite(Mono<DeleteFavoriteRequest> favoriteRequest) {
        log.info("Delete favorite");
        return favoriteRequest.flatMap(postFavoriteRequest -> {
            log.debug("Object favoriteRequest Request in Json: [request:{}]", new Gson().toJson(postFavoriteRequest));

            return favoritesRepository.findById(postFavoriteRequest.getFavoriteId())
                    .flatMap(favoritesEntity -> favoritesRepository.deleteById(favoritesEntity.getFavoritesId())
                            .then(Mono.defer(() -> charactersRepository.deleteById(favoritesEntity.getCharactersId())
                                    .then(Mono.defer(() -> Mono.just(ResponseEntity.ok().body(null)))))));

        });
    }

    @Override
    public Mono<ResponseEntity<Mono<GetFavoritesResponse>>> getFavorites(Integer userId) {
        log.info("Get favorites");
        return favoritesRepository.findByUserId(userId)
                .collectList()
                .map(charactersEntities -> {
                    List<FavoritesEntity> entities = new ArrayList<>(charactersEntities);

//                    for(FavoritesEntity entity: entities) {
//                        charactersRepository.findById(entity.getFavoritesId())
//
//                    }

//                    charactersRepository.find

                    GetFavoritesResponse getFavoritesResponse = new GetFavoritesResponse();
                    getFavoritesResponse.setUserId(userId);
                    getFavoritesResponse.setCharacters(entities);
                    return ResponseEntity.ok().body(Mono.just(getFavoritesResponse));
                });
    }

}
