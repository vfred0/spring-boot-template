package com.gnoboa.configurations.jwt;

import com.gnoboa.data.daos.IUserRepository;
import com.gnoboa.services.auth.JwtAccessTokenService;
import com.gnoboa.services.exceptions.MessageException;
import com.gnoboa.services.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    private final IUserRepository userAccountRepository;
    private final JwtAccessTokenService jwtAccessTokenService;

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        String dni = jwt.getClaim(JwtClaimNames.SUB);
        if (jwtAccessTokenService.isValid(jwt) && userAccountRepository.existsByDni(dni)) {
            Collection<GrantedAuthority> authorities = extractRolesFromClaims(jwt);
            return new JwtAuthenticationToken(jwt, authorities, dni);
        }
        throw new NotFoundException(MessageException.USER_NOT_FOUND);
    }

    @SuppressWarnings("unchecked")
    private Collection<GrantedAuthority> extractRolesFromClaims(Jwt jwt) {
        if (jwt.hasClaim("roles")) {
            Object authorities = jwt.getClaim("roles");
            if (authorities instanceof Collection) {
                return ((Collection<String>) authorities).stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
            }
        }
        return Collections.emptyList();
    }
}