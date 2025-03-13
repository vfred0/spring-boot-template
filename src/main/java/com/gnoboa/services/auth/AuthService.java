package com.gnoboa.services.auth;


import com.gnoboa.api.dtos.auth.AccessTokenDto;
import com.gnoboa.api.dtos.auth.LoginRequestDto;
import com.gnoboa.data.daos.IUserAccountRepository;
import com.gnoboa.data.daos.IUserRepository;
import com.gnoboa.data.daos.IUserRoleRepository;
import com.gnoboa.data.entities.identity.UserAccount;
import com.gnoboa.data.enums.Role;
import com.gnoboa.services.exceptions.MessageException;
import com.gnoboa.services.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Random;


@Component
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtAccessTokenService jwtAccessTokenService;
    private final IUserAccountRepository userAccountRepository;
    private final IUserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final IUserRepository userRepository;

    public AccessTokenDto authenticate(LoginRequestDto loginRequestDto) {
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
                loginRequestDto.dni(),
                loginRequestDto.otp()
        );
        Authentication authentication = this.authenticationManager.authenticate(authenticationToken);

        String token = this.jwtAccessTokenService.generateToken(authentication);
        return new AccessTokenDto(token);
    }

    public void processOfSetOtp(DniRequestDto dniRequestDto) {
        this.userRepository.findByDni(dniRequestDto.dni()).orElseThrow(() -> new NotFoundException(MessageException.USER_NOT_FOUND));
        String otp = String.format("%06d", new Random().nextInt(1000000));
        UserAccount userAccount = this.userAccountRepository.findByDni(dniRequestDto.dni()).orElseGet(() -> UserAccount.builder()
                .dni(dniRequestDto.dni())
                .role(this.userRoleRepository.findByName(Role.AUTHENTICATED))
                .build());
        userAccount.setOtp(this.passwordEncoder.encode(otp));
        this.userAccountRepository.save(userAccount);
        log.info("OTP: {}", otp);
    }
}
