package com.pichincha.flight.management.services.domain.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostLoginUserRequest {
    String email;
    String password;
}
