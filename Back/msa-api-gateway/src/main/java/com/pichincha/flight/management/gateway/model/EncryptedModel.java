package com.pichincha.flight.management.gateway.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Generated
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EncryptedModel {
    String content;
    String encryptionData;
}
