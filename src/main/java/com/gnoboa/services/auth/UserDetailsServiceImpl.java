package com.gnoboa.services.auth;


import com.gnoboa.data.daos.IUserAccountRepository;
import com.gnoboa.data.entities.identity.UserAccount;
import com.gnoboa.services.exceptions.MessageException;
import com.gnoboa.services.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUserAccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.accountRepository
                .findByDni(username)
                .map(this::map)
                .orElseThrow(() -> new NotFoundException(MessageException.USER_NOT_FOUND));
    }

    private User map(UserAccount userAccount) {
        return new User(
                userAccount.getDni(),
                String.valueOf(userAccount.getOtp()),
                Set.of(userAccount.getRole())
        );
    }
}