package com.tanvantran.service;

import java.net.Authenticator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
//import org.springframework.data.jpa.repository.query.EqlParser.New_valueContext;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tanvantran.entity.Account;
import com.tanvantran.entity.Department;
import com.tanvantran.entity.Position;
import com.tanvantran.form.AccountFormForCreating;
import com.tanvantran.form.AccountFormForUpdating;
import com.tanvantran.repository.IAccountRepository;
import com.tanvantran.repository.IDepartmentRepository;
import com.tanvantran.repository.IPositionRepository;
import com.tanvantran.specification.AccountSpecification;

import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;

@Service
//@Transactional
public class AccountService implements IAccountService {
	
	@Autowired
	private IAccountRepository accountRepository;
	
	@Autowired
	private IDepartmentRepository departmentRepository;
	
	@Autowired
	private IPositionRepository positionRepository;

	@Override
	public Page<Account> getAllAccount(Pageable pageable, String search) {
		
		// Điều kiện tổng
		Specification<Account> where = null;
		
		if (!StringUtils.isEmpty(search)) {
			// fullname LIKE search
			AccountSpecification fullnameAccountSpecification = new AccountSpecification("fullname", "LIKE", search);
			// email LIKE search
			AccountSpecification emailAccountSpecification = new AccountSpecification("email", "LIKE", search);
			// department LIKE search
			AccountSpecification departmentAccountSpecification = new AccountSpecification("department", "LIKE", search);
			// username LIKE search
			AccountSpecification usernameAccountSpecification = new AccountSpecification("username", "LIKE", search);
			
			where = Specification.where(fullnameAccountSpecification).or(emailAccountSpecification)
																	.or(departmentAccountSpecification).or(usernameAccountSpecification);
		}
		
		return accountRepository.findAll(where, pageable);
	}

	@Override
	public Account getAccountById(Short id) {
		// TODO Auto-generated method stub
		return accountRepository.getById(id);
	}

	@Override
	public Account createAccount(AccountFormForCreating formCreating) {
		Account account = new Account();
		account.setEmail(formCreating.getEmail());
		account.setUsername(formCreating.getUsername());
		account.setFullname(formCreating.getFullname());
		
//		departmentId ==> tìm ra DepartmentService tương ứng
		Department department = departmentRepository.getById(formCreating.getDepartmentId());
		account.setDepartment(department);
		
//		positionId --> tìm ra Position tương ứng
		Position position = positionRepository.getById(formCreating.getDepartmentId());
		account.setPosition(position);
		
		Account newAccount = accountRepository.save(account);
		return newAccount;
	}

	@Override
	public Account updateAccount(short id, AccountFormForUpdating formUpdating) {
		// id tìm ra Account tương ứng
		Account account = accountRepository.getById(id);
		
		account.setFullname(formUpdating.getFullname());
		
		Department department = departmentRepository.getById(formUpdating.getDepartmentId());
		account.setDepartment(department);
		
		Position position = positionRepository.getById(formUpdating.getPositionId());
		account.setPosition(position);
		
		Account updateAccount = accountRepository.save(account);
		return updateAccount;
	}

	@Override
	public void deleteAccount(short id) {
		accountRepository.deleteById(id);
//		accountRepository.findBy
	}

	@Override
	public Account getAccountByUsername(String username) {
		Account account = accountRepository.findByUsername(username)
				.orElseThrow(()-> new IllegalArgumentException("Username not Found: " + username));
		return account;
	}

	@Override
	public Account getAccountByEmail(String email) {
		Account account = accountRepository.findByEmail(email)
				.orElseThrow(()-> new IllegalArgumentException("Email not Found: " + email));
		return null;
	}
	
	@Override
	public boolean existsByUsernameOrEmail(String username, String email) {
		
		return accountRepository.existsByUsernameOrEmail(username, email);
	}

	@Override
	public Account getLastestAccount() {
		// TODO Auto-generated method stub
		return accountRepository.findTopByOrderByIdDesc();
	}

	@Override
	public List<Account> getAccountByIdRange(short idFrom, short idTo) {
		// TODO Auto-generated method stub
		return accountRepository.findByIdBetween(idFrom, idTo);
	}

	@Override
	public List<Account> getAccountByIds(List<Short> ids) {
		// TODO Auto-generated method stub
		return accountRepository.findAllById(ids);
	}

	@Override
	public List<String> getAllUsernames() {
		// TODO Auto-generated method stub
		return accountRepository.getAllUsernames();
	}

	@Override
	public List<Account> getAccountByIdDepartment(short departmentId) {
		// TODO Auto-generated method stub
		return accountRepository.getAccountByIdDepartment(departmentId);
	}

	@Override
	public List<Account> getAccountByPosition(String positionName) {
		// TODO Auto-generated method stub
		return accountRepository.getAccountByPosition(positionName);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepository.findByUsername(username).orElse(null);
		if (account == null) {
			throw new UsernameNotFoundException(username);
		}
		//ROLE_ADMIN
		//ROLE_USER
		UserDetails userDetails = new User(account.getUsername(), account.getPassword(), 
							AuthorityUtils.createAuthorityList("ROLE_" + account.getRole()));
		return userDetails;
	}

}
