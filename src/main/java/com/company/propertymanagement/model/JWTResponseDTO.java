package com.company.propertymanagement.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JWTResponseDTO {

    private String token;
    private String type = "Bearer";
    private Long id;
}
