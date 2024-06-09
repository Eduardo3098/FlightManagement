package com.pichincha.flight.management.services.domain.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Generated
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostLoginRequest {
    String name;
    String userName;
    String nickname;
    List<String> roles;
}
