package com.tanvantran.service;

import java.util.List;

import com.tanvantran.dto.AccountResponseDto;
import com.tanvantran.entity.Account;
import com.tanvantran.form.AccountFormForCreating;


public interface IAccountService {

    List<Account> getAllAccount();

    AccountResponseDto getAccountById(short id);

    void deleteAccount(short id);

    Account createAccount(AccountFormForCreating form);

    Account getAccountByID(short id);


}



