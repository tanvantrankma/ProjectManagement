package com.vti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vti.dto.DepartmentDto;
import com.vti.dto.PossitonDto;
import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.entity.Position;
import com.vti.entity.Position.PositionName;
import com.vti.feignclient.DepartmentFeignClient;
import com.vti.feignclient.PositionFeignClient;
import com.vti.form.AccountFormForCreating;
import com.vti.repository.IAccountRepository;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private DepartmentFeignClient dpFeignClient;

    @Autowired
    private PositionFeignClient positionFeignClient;

    @Override
    public List<Account> getAllAccount() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccountById(short id) {
        return accountRepository.getById(id);
    }

    @Override
    public void deleteAccount(short id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Account createAccount(AccountFormForCreating form) {
        Account account = new Account();
        // Department department = departmentRepository.getById(form.getDepartmentId());
        short depID = form.getDepartmentId();

        // Sử dụng Feign lấy dư liệu Department từ DepartmentService

        ResponseEntity<DepartmentDto> departmentResponseEntity = dpFeignClient.getDepartmentByID(depID);
        System.out.println("Response From Feignclient Department Info **********************");
        System.out.println("Response From Feignclient Department Info **********************");
        System.out.println(departmentResponseEntity.getBody());

        // Chuyển đổi sang đối tượng phòng ban đang có trên hệ thống để lưu trữ vào
        // Account mới
        Department department = new Department();
        department.setId(departmentResponseEntity.getBody().getId());
        department.setName(departmentResponseEntity.getBody().getName());

        // Position position = possitionRepository.getById(form.getPositionId());
        // Department department = departmentRepository.getById(form.getDepartmentId());

        // Sử dụng Feign lấy dư liệu Positon từ Position Service
        ResponseEntity<PossitonDto> positionResponseEntity = positionFeignClient.getPositionByID(depID);
        System.out.println("Response From Feignclient Position Info **********************");
        System.out.println("Response From Feignclient Position Info **********************");
        System.out.println(positionResponseEntity.getBody());

        // Chuyển đổi sang đối tượng Positon đang có trên hệ thống để lưu trữ vào
        // Account mới
        Position position = new Position();
        position.setId(positionResponseEntity.getBody().getId());
        position.setName(PositionName.valueOf(positionResponseEntity.getBody().getName()));

        // Set dữ liệu cho Account mới
        account.setEmail(form.getEmail());
        account.setUsername(form.getUsername());
        account.setFullname(form.getFullname());
        account.setDepartment(department);
        account.setPosition(position);

        Account accountNew = accountRepository.save(account);
        return accountNew;
    }

}



