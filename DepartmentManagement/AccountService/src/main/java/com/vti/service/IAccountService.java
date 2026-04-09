package com.vti.service;

import java.util.List;

import com.vti.entity.Account;
import com.vti.form.AccountFormForCreating;

public interface IAccountService {

    List<Account> getAllAccount();

    Account getAccountById(short id);

    void deleteAccount(short id);

    Account createAccount(AccountFormForCreating form);

}



