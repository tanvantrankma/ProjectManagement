package com.tanvantran.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tanvantran.entity.Account;


public interface IAccountRepository extends JpaRepository<Account, Short> {
    Account findByUsername(String username);

    boolean existsByUsername(String username);

}


