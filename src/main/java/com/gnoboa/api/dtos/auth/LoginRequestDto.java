package com.gnoboa.api.dtos.auth;


public record LoginRequestDto(
        String username,
        String password
) {
}