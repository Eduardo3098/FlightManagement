package com.pichincha.flight.management.services.domain.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("favorites")
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FavoritesEntity {
    @Id()
    @Column("favorites_id")
    private Integer favoritesId;

    @Column("user_id")
    private Integer userId;

    @Column("characters_id")
    private Integer charactersId;

//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "character_id")
//    private CharactersEntity characters;
}
