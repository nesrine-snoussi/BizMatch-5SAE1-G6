package tn.esprit.authenbizmatch.entities;

import lombok.Getter;

@Getter
public class AuthResponse {
    private final String jwt;

    public AuthResponse(String jwt) {
        this.jwt = jwt;
    }
}
