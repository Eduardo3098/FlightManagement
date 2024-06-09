package com.pichincha.flight.management.services.domain.models;

import com.pichincha.flight.management.services.domain.entities.FavoritesEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetFavoritesResponse {
    Integer userId;
    List<FavoritesEntity> characters;
}

