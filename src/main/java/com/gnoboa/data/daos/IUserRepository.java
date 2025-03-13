package com.gnoboa.data.daos;


import com.gnoboa.data.entities.User;

import java.util.Optional;

public interface IUserRepository extends IRepository<User, Integer> {

    Optional<User> findByUsername(String dni);

    boolean existsByUsername(String dni);


}