package com.gnoboa.services.auth;


import com.gnoboa.api.dtos.auth.AccessTokenDto;
import com.gnoboa.api.dtos.auth.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtAccessTokenService jwtAccessTokenService;

    public AccessTokenDto authenticate(LoginRequestDto loginRequestDto) {
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
                loginRequestDto.dni(),
                loginRequestDto.otp()
        );
        Authentication authentication = this.authenticationManager.authenticate(authenticationToken);

        String token = this.jwtAccessTokenService.generateToken(authentication);
        return new AccessTokenDto(token);
    }
}
