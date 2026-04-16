package com.tanvantran.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tanvantran.dto.AccountResponseDto;
import com.tanvantran.dto.OrderDto;
import com.tanvantran.dto.ProductDto;
import com.tanvantran.entity.Account;
import com.tanvantran.feignclient.OrderFeignClient;
import com.tanvantran.feignclient.ProductFeignClient;
import com.tanvantran.form.AccountFormForCreating;
import com.tanvantran.repository.IAccountRepository;


@Service
public class AccountService implements IAccountService {
    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private OrderFeignClient odFeignClient;

    @Autowired
    private ProductFeignClient productFeignClient;

    @Override
    public List<Account> getAllAccount() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccountByID(short id) {
        return accountRepository.getById(id);
    }


    @Override
    public AccountResponseDto getAccountById(short id) {

        // Account account = accountRepository.findById(id).get();

        // short odID = account.getOrderID();

        // // short odID = form.getOrderId();

        // ResponseEntity<OrderDto> orderResponseEntity = odFeignClient.getOrderById(odID);
        // System.out.println("Response From Feignclient Order Info **********************");
        // System.out.println("Response From Feignclient Order Info **********************");
        // System.out.println(orderResponseEntity.getBody());

        // // Chuyển đổi sang đối tượng phòng ban đang có trên hệ thống để lưu trữ vào
        // // Account mới
        // OrderDto orderDto = new OrderDto();
        // orderDto.setId(orderResponseEntity.getBody().getId());
        // orderDto.setName(orderResponseEntity.getBody().getName());

        // // ================================================
        
        // short prID = account.getProductID();

        // ResponseEntity<ProductDto> productResponseEntity = productFeignClient.getPositionByID(prID);
        // System.out.println("Response From Feignclient Product Info **********************");
        // System.out.println("Response From Feignclient Product Info **********************");
        // System.out.println(productResponseEntity.getBody());

        // // Chuyển đổi sang đối tượng Positon đang có trên hệ thống để lưu trữ vào
        // // Account mới
        // ProductDto productDto = new ProductDto();
        // productDto.setId(productResponseEntity.getBody().getId());
        // productDto.setName(productResponseEntity.getBody().getName());

        // // Set dữ liệu cho Account mới
        
        // account.setEmail(account.getEmail());
        // account.setUsername(account.getUsername());
        // account.setFullname(account.getFullname());
        // account.setOrderID(odID);
        // account.setProductID(prID);


        // Account accountNew = accountRepository.save(account);



        // return accountNew;

        // lấy account từ database
        // 1. Lấy Account từ DB
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        // 2. Gọi OrderService
        ResponseEntity<OrderDto> orderResponse =
                odFeignClient.getOrderById(account.getOrderID());

        OrderDto orderDto = orderResponse.getBody();

        System.out.println("Response From OrderService");
        System.out.println(orderDto);

        // 3. Gọi ProductService
        ResponseEntity<ProductDto> productResponse =
                productFeignClient.getProductByID(account.getProductID());

        ProductDto productDto = productResponse.getBody();

        System.out.println("Response From ProductService");
        System.out.println(productDto);

        // 4. Map dữ liệu trả về
        AccountResponseDto dto = new AccountResponseDto();

        dto.setId(account.getId());
        dto.setEmail(account.getEmail());
        dto.setUsername(account.getUsername());
        dto.setFullname(account.getFullname());

        dto.setOrder(orderDto);
        dto.setProduct(productDto);

        return dto;

    }

    @Override
    public void deleteAccount(short id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Account createAccount(AccountFormForCreating form) {
        // TODO Auto-generated method stub
        Account account = new Account();
        // Department department = departmentRepository.getById(form.getDepartmentId());
        short odID = form.getOrderId();

        // Sử dụng Feign lấy dư liệu Department từ DepartmentService

        ResponseEntity<OrderDto> orderResponseEntity = odFeignClient.getOrderById(odID);
        System.out.println("Response From Feignclient Order Info **********************");
        System.out.println("Response From Feignclient Order Info **********************");
        System.out.println(orderResponseEntity.getBody());

        // Chuyển đổi sang đối tượng phòng ban đang có trên hệ thống để lưu trữ vào
        // Account mới
        OrderDto orderDto = new OrderDto();
        orderDto.setId(orderResponseEntity.getBody().getId());
        orderDto.setOrderNumber(orderResponseEntity.getBody().getOrderNumber());

        // ======

        short prID = form.getProductId();

        ResponseEntity<ProductDto> productResponseEntity = productFeignClient.getProductByID(prID);
        System.out.println("Response From Feignclient Product Info **********************");
        System.out.println("Response From Feignclient Product Info **********************");
        System.out.println(productResponseEntity.getBody());

        // Chuyển đổi sang đối tượng Positon đang có trên hệ thống để lưu trữ vào
        // Account mới
        ProductDto productDto = new ProductDto();
        productDto.setId(productResponseEntity.getBody().getId());
        productDto.setName(productResponseEntity.getBody().getName());

        // Set dữ liệu cho Account mới
        account.setEmail(form.getEmail());
        account.setUsername(form.getUsername());
        account.setFullname(form.getFullname());
        account.setOrderID(odID);
        account.setProductID(prID);
        // account.setOrderID(orderDto);
        // account.setProductID(productDto);

        Account accountNew = accountRepository.save(account);
        return accountNew;
    }

    

    
}



