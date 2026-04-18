package com.tanvantran.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tanvantran.entity.Account;
import com.tanvantran.form.AccountFormForCreating;
import com.tanvantran.form.AccountFormForUpdating;

public interface IAccountService {

	Page<Account> getAllAccount(Pageable pageable, String search);

	Account getAccountById(Short id);

	Account createAccount(AccountFormForCreating formCreating);

	Account updateAccount(short id, AccountFormForUpdating formUpdating);

	void deleteAccount(short id);

	Account getAccountByUsername(String username);

	Account getAccountByEmail(String email);

	boolean existsByUsernameOrEmail(String username, String email);

	Account getLastestAccount();

	List<Account> getAccountByIdRange(short idFrom, short idTo);

	List<Account> getAccountByIds(List<Short> ids);

	List<String> getAllUsernames();

	List<Account> getAccountByIdDepartment(short departmentId);

	List<Account> getAccountByPosition(String positionName);

}
