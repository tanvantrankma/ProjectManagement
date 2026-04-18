package com.tanvantran.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.sql.Update;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tanvantran.dto.AccountDto;
import com.tanvantran.dto.UsernameDto;
import com.tanvantran.entity.Account;
import com.tanvantran.form.AccountFormForCreating;
import com.tanvantran.form.AccountFormForUpdating;
import com.tanvantran.service.IAccountService;

@RestController
@RequestMapping(value = "api/v1/accounts")
@CrossOrigin("*")
public class AccountController {
	
	@Autowired
	private IAccountService accountService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping()
	public ResponseEntity<?> getAllAccount(Pageable pageable, @RequestParam(required = false) String search) {
		Page<Account> pageAccounts = accountService.getAllAccount(pageable, search);
		
//		List<AccountDto> listAccountDtos = new ArrayList<>();
		
//		for (Account account : listAccounts) {
//			AccountDto accountDto = new AccountDto();
//			accountDto.setId(account.getId());
//			accountDto.setEmail(account.getEmail());
//			accountDto.setUsername(account.getUsername());
//			accountDto.setFullname(account.getFullname());
//			accountDto.setDepartment(account.getDepartment().getName());
//			accountDto.setPosition(account.getPosition().getName().toString());
//			accountDto.setCreateDate(account.getCreateDate());
//			
//			listAccountDtos.add(accountDto);
//		}
		
		// Stream
		Page<AccountDto> pageAccountDto = pageAccounts
				.map(account -> {
					AccountDto acountDto = modelMapper.map(account, AccountDto.class);
					acountDto.setPosition(account.getPosition().getName().toString());
					return acountDto;
							});
			
		return new ResponseEntity<>(pageAccounts, HttpStatus.OK);
	}
	
	// getAccountById()
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getAccountById(@PathVariable(name = "id") Short id) {
		Account account = accountService.getAccountById(id);
		
		AccountDto accountDto = new AccountDto();
		accountDto.setId(account.getId());
		accountDto.setEmail(account.getEmail());
		accountDto.setUsername(account.getUsername());
		accountDto.setFullname(account.getFullname());
		accountDto.setDepartment(account.getDepartment().getName());
		accountDto.setPosition(account.getPosition().getName().toString());
		accountDto.setCreateDate(account.getCreateDate());
		
		return new ResponseEntity<>(accountDto, HttpStatus.OK);
	}
	
	// Tạo mới Account
	
	@PostMapping()
	public ResponseEntity<?> createAccount(@RequestBody AccountFormForCreating formCreating) {
		Account account = accountService.createAccount(formCreating);
		
		return new ResponseEntity<>(account, HttpStatus.OK);
		
//		return new ResponseEntity<>(accountDto, HttpStatus.OK);
//		return new ResponseEntity<>("Tạo mới Account thành công !", HttpStatus.OK);
	}
	
	// Update Account
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateAccount(@RequestBody AccountFormForUpdating formUpdating, @PathVariable(name = "id") short id) {
		Account account = accountService.updateAccount(id, formUpdating);
		
		// Chuyển đổi thành DTO
		AccountDto accountDto = new AccountDto();
		accountDto.setId(account.getId());
		accountDto.setEmail(account.getEmail());
		accountDto.setUsername(account.getUsername());
		accountDto.setFullname(account.getFullname());
		accountDto.setDepartment(account.getDepartment().getName());
		accountDto.setPosition(account.getPosition().getName().toString());
		accountDto.setCreateDate(account.getCreateDate());
		
		return new ResponseEntity<>(accountDto, HttpStatus.OK);
	}
	
	// Delete Account
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteAccount(@PathVariable(name = "id") short id) {
		accountService.deleteAccount(id);
	
		return new ResponseEntity<>("Xoá Account thành công !", HttpStatus.OK);
	}
	
	// Tìm Account theo Username
	
	@GetMapping(value = "/username/{username}")
	public ResponseEntity<?> getAccountByUsername(@PathVariable(name = "username") String username) {
		Account account = accountService.getAccountByUsername(username);
		
		AccountDto accountDto = new AccountDto();
		accountDto.setId(account.getId());
		accountDto.setEmail(account.getEmail());
		accountDto.setUsername(account.getUsername());
		accountDto.setFullname(account.getFullname());
		accountDto.setDepartment(account.getDepartment().getName());
		accountDto.setPosition(account.getPosition().getName().toString());
		accountDto.setCreateDate(account.getCreateDate());
		
		return new ResponseEntity<>(accountDto, HttpStatus.OK);
	}
	
	// Tìm Account theo Email
	
	@GetMapping(value = "/email/{email}")
	public ResponseEntity<?> getAccountByEmail(@PathVariable(name = "email") String email) {
		Account account = accountService.getAccountByEmail(email);
		
		AccountDto accountDto = new AccountDto();
		accountDto.setId(account.getId());
		accountDto.setEmail(account.getEmail());
		accountDto.setUsername(account.getUsername());
		accountDto.setFullname(account.getFullname());
		accountDto.setDepartment(account.getDepartment().getName());
		accountDto.setPosition(account.getPosition().getName().toString());
		accountDto.setCreateDate(account.getCreateDate());
		
		return new ResponseEntity<>(accountDto, HttpStatus.OK);
	}
	
	// Kiểm tra sự tồn tại của username hoặc email
	@GetMapping(value = "/existsByUsernameOrEmail")
	public ResponseEntity<?> existsByUsernameOrEmail(@RequestParam String username, @RequestParam String email) {
		boolean result = accountService.existsByUsernameOrEmail(username, email);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	// Tìm Account mới nhất trên hệ thống
	@GetMapping(value = "/lastest")
	public ResponseEntity<?> getLastestAccount() {
		Account account = accountService.getLastestAccount();
		
		AccountDto accountDto = new AccountDto();
		accountDto.setId(account.getId());
		accountDto.setEmail(account.getEmail());
		accountDto.setUsername(account.getUsername());
		accountDto.setFullname(account.getFullname());
		accountDto.setDepartment(account.getDepartment().getName());
		accountDto.setPosition(account.getPosition().getName().toString());
		accountDto.setCreateDate(account.getCreateDate());
		
		return new ResponseEntity<>(accountDto, HttpStatus.OK);
	}
	
	// Tìm Account trong khoảng id: idFrom = 3, idTo = 6
	@GetMapping(value = "/range")
	public ResponseEntity<?> getAccountByIdRange(@RequestParam short idFrom, @RequestParam short idTo) {
		List<Account> listAccounts = accountService.getAccountByIdRange(idFrom, idTo);
		
		List<AccountDto> listAccountDtos = new ArrayList<>();
		for (Account account : listAccounts) {
			AccountDto accountDto = new AccountDto();
			accountDto.setId(account.getId());
			accountDto.setEmail(account.getEmail());
			accountDto.setUsername(account.getUsername());
			accountDto.setFullname(account.getFullname());
			accountDto.setDepartment(account.getDepartment().getName());
			accountDto.setPosition(account.getPosition().getName().toString());
			accountDto.setCreateDate(account.getCreateDate());
			
			listAccountDtos.add(accountDto);
		}
		
		return new ResponseEntity<>(listAccountDtos, HttpStatus.OK);
	}
	
	// Tìm list Account có các id [1, 3, 4, 8]
	@GetMapping(value = "/byIds")
	public ResponseEntity<?> getAccountByIds(@RequestBody List<Short> ids) {
		List<Account> listAccounts = accountService.getAccountByIds(ids);
		
		List<AccountDto> listAccountDtos = new ArrayList<>();
		for (Account account : listAccounts) {
			AccountDto accountDto = new AccountDto();
			accountDto.setId(account.getId());
			accountDto.setEmail(account.getEmail());
			accountDto.setUsername(account.getUsername());
			accountDto.setFullname(account.getFullname());
			accountDto.setDepartment(account.getDepartment().getName());
			accountDto.setPosition(account.getPosition().getName().toString());
			accountDto.setCreateDate(account.getCreateDate());
			
			listAccountDtos.add(accountDto);
		}
		
		return new ResponseEntity<>(listAccountDtos, HttpStatus.OK);
	}
	
	// JPQL
	// Lấy danh sách Username đang có trên hệ thống (lấy trực tiếp)
	@GetMapping(value = "/usernames")
	public ResponseEntity<?> getAllUsernames() {
		List<String> usernames = accountService.getAllUsernames();
		
		List<UsernameDto> listUsernameDtos = new ArrayList<>();
		for (String username : usernames) {
			UsernameDto usernameDto = new UsernameDto();
			usernameDto.setUsername(username);
			
			listUsernameDtos.add(usernameDto);
		}
	
		return new ResponseEntity<>(listUsernameDtos, HttpStatus.OK);
	}
	
	// JPQL
	// Tìm danh sách Account theo id phòng ban truyền vào
	@GetMapping(value = "/departmentId/{departmentId}")
	public ResponseEntity<?> getAccountByIdDepartment(@PathVariable short departmentId) {
		List<Account> listAccounts = accountService.getAccountByIdDepartment(departmentId);
		
		List<AccountDto> listAccountDtos = new ArrayList<>();
		for (Account account : listAccounts) {
			AccountDto accountDto = new AccountDto();
			accountDto.setId(account.getId());
			accountDto.setEmail(account.getEmail());
			accountDto.setUsername(account.getUsername());
			accountDto.setFullname(account.getFullname());
			accountDto.setDepartment(account.getDepartment().getName());
			accountDto.setPosition(account.getPosition().getName().toString());
			accountDto.setCreateDate(account.getCreateDate());
			
			listAccountDtos.add(accountDto);
		}
		
		return new ResponseEntity<>(listAccountDtos, HttpStatus.OK);
	}
	
	// JPQL
	// Tìm danh sách Account theo tên của Position : Dev
	@GetMapping(value = "/position/{positionName}")
	public ResponseEntity<?> getAccountByPosition(@PathVariable String positionName) {
		List<Account> listAccounts = accountService.getAccountByPosition(positionName);
		
		List<AccountDto> listAccountDtos = new ArrayList<>();
		for (Account account : listAccounts) {
			AccountDto accountDto = new AccountDto();
			accountDto.setId(account.getId());
			accountDto.setEmail(account.getEmail());
			accountDto.setUsername(account.getUsername());
			accountDto.setFullname(account.getFullname());
			accountDto.setDepartment(account.getDepartment().getName());
			accountDto.setPosition(account.getPosition().getName().toString());
			accountDto.setCreateDate(account.getCreateDate());
			
			listAccountDtos.add(accountDto);
		}
		
		return new ResponseEntity<>(listAccountDtos, HttpStatus.OK);
	}
}
