package com.pichincha.flight.management.services.domain.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("characters")
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CharactersFavoritesEntity {
    @Id()
    @Column("characters_id")
    private Integer charactersId;

    @Column("favorites_id")
    private Integer favoritesId;
}
