package com.gnoboa.api.dtos.auth;


public record LoginRequestDto(
        String dni,
        String otp
) {
}