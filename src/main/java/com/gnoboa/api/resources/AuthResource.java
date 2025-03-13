package com.gnoboa.api.resources;

import com.gnoboa.api.dtos.auth.AccessTokenDto;
import com.gnoboa.api.dtos.auth.LoginRequestDto;
import com.gnoboa.services.auth.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/auth")
public class AuthResource {

    private final AuthService authService;

    @PostMapping("login")
    public ResponseEntity<AccessTokenDto> login(
            @RequestBody LoginRequestDto loginRequestDto) {
        AccessTokenDto accessTokenDto = authService.authenticate(loginRequestDto);
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, createCookie(accessTokenDto.token()))
                .body(accessTokenDto);
    }


    private String createCookie(String value) {
        return ResponseCookie.from("access_token", value)
                .maxAge(806400)
                .httpOnly(true)
                .secure(false)
                .sameSite("Strict")
                .path("/").build().toString();
    }


    @PostMapping("logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from("access_token", "")
                .maxAge(0)
                .httpOnly(true)
                .secure(false)
                .sameSite("Strict")
                .path("/").build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}