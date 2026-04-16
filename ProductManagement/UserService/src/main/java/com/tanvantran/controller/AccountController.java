package com.tanvantran.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanvantran.dto.AccontDto;
import com.tanvantran.dto.AccountResponseDto;
import com.tanvantran.entity.Account;
import com.tanvantran.form.AccountFormForCreating;
import com.tanvantran.rabbitmqClient.service.RabbitMQSender;
import com.tanvantran.service.IAccountService;


@RestController
@RequestMapping(value = "api/v1/accounts")
@CrossOrigin("*")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @Autowired
    private RabbitMQSender rabbitMQSender;

    // Phương thức này không thay đổi so với trước đây
    @GetMapping()
    public ResponseEntity<?> getAllAccount() {
        List<Account> listAccounts = accountService.getAllAccount();
        List<AccontDto> listAccountDTOs = new ArrayList<>();

        for (Account account : listAccounts) {
            AccontDto accountDTO = new AccontDto();
            accountDTO.setId(account.getId());
            accountDTO.setEmail(account.getEmail());
            accountDTO.setFullname(account.getFullname());
            accountDTO.setUsername(account.getUsername());
            accountDTO.setOrder(account.getOrderID());
            accountDTO.setProduct(account.getProductID());
            accountDTO.setCreateDate(account.getCreateDate());

            listAccountDTOs.add(accountDTO);
        }
        return new ResponseEntity<>(listAccountDTOs, HttpStatus.OK);
    }

    // Phương thức này không thay đổi so với trước đây
    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDto> getAccountByID(@PathVariable short id) {

        AccountResponseDto account = accountService.getAccountById(id);

        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    // Phương thức này không thay đổi so với trước đây
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable(name = "id") short id) {
        accountService.deleteAccount(id);
        return new ResponseEntity<String>("Delete successfully!", HttpStatus.OK);
    }

    // Tạo mới Account
    @PostMapping()
    public ResponseEntity<?> createNewAccount(@RequestBody AccountFormForCreating form) {
        Account accountNew = accountService.createAccount(form);

        // Gửi sự kiện đến notifyService
        rabbitMQSender.sendAccountCreatedEvent(accountNew);

        return new ResponseEntity<Account>(accountNew, HttpStatus.CREATED);
    }

}


