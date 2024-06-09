package com.pichincha.flight.management.services.domain.repository;

import com.pichincha.flight.management.services.domain.entities.FavoritesEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface FavoritesRepository extends ReactiveCrudRepository<FavoritesEntity, Integer> {
    @Query("select * from favorites where user_id = :userId")
    Flux<FavoritesEntity> findByUserId(Integer userId);
}
