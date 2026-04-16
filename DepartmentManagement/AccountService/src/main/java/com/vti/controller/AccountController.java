package com.vti.controller;

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

import com.vti.dto.AccontDto;
import com.vti.entity.Account;
import com.vti.form.AccountFormForCreating;
import com.vti.rabbitmqClient.service.RabbitMQSender;
import com.vti.service.IAccountService;

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
            accountDTO.setDepartment(account.getDepartment().getName());
            accountDTO.setPosition(account.getPosition().getName().toString());
            accountDTO.setCreateDate(account.getCreateDate());

            listAccountDTOs.add(accountDTO);
        }
        return new ResponseEntity<>(listAccountDTOs, HttpStatus.OK);
    }

    // Phương thức này không thay đổi so với trước đây
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getAccountByID(@PathVariable(name = "id") short id) {
        Account account = accountService.getAccountById(id);

        AccontDto accountDTO = new AccontDto();
        accountDTO.setId(account.getId());
        accountDTO.setEmail(account.getEmail());
        accountDTO.setFullname(account.getFullname());
        accountDTO.setUsername(account.getUsername());
        accountDTO.setDepartment(account.getDepartment().getName());
        accountDTO.setPosition(account.getPosition().getName().toString());
        accountDTO.setCreateDate(account.getCreateDate());

        return new ResponseEntity<>(accountDTO, HttpStatus.OK);
    }

    // Phương thức này không thay đổi so với trước đây
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable(name = "id") short id) {
        accountService.deleteAccount(id);
        return new ResponseEntity<String>("Delete successfully!", HttpStatus.OK);
    }

    // Phương thức này cần tìm ra Department và Position dựa vào ID được truyền về
    // từ Frontend
    // Cần sử dụng RestTempalte hoặc Feigin Client để call API tới các Service
    // Department và Position

    // Tạo mới Account
    @PostMapping()
    public ResponseEntity<?> createNewAccount(@RequestBody AccountFormForCreating form) {
        Account accountNew = accountService.createAccount(form);

        // Tạo mới Account thành công, sẽ gửi bản tin lên RabitMQ để thông báo cho NotifyService biết về sự kiện này 
        // và gửi thông báo cho người dùng
        // Hàm Gửi sự kiện đến notifyService
        rabbitMQSender.sendAccountCreatedEvent(accountNew);


        return new ResponseEntity<Account>(accountNew, HttpStatus.CREATED);
    }

}



