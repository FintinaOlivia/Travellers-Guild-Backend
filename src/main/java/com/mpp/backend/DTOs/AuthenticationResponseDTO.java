package com.mpp.backend.DTOs;


import lombok.Data;

@Data
public class AuthenticationResponseDTO {
    private String accessToken;
    private String tokenType = "Bearer ";

    public AuthenticationResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }
}
