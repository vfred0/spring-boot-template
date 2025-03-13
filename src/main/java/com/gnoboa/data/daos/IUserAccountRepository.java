package com.gnoboa.data.daos;

import com.gnoboa.data.entities.identity.UserAccount;

import java.util.Optional;
import java.util.UUID;

public interface IUserAccountRepository extends IRepository<UserAccount, UUID> {
    Optional<UserAccount> findByDni(String dni);
}
