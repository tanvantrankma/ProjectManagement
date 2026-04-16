package com.vti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vti.authen.JwtUtil;
import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.entity.Position;
import com.vti.form.AuthRequest;
import com.vti.form.RegisterRequest;
import com.vti.repository.IAccountRepository;

@Service
public class AuthService implements IAuthService{

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String login(AuthRequest request) {
        // TODO Auto-generated method stub

        // Tìm xem có tồn tại AccountLogin đang nằm dưới DB không
        Account acc_login = accountRepository.findByUsername(request.getUsername());
        if (acc_login == null) {
            throw new RuntimeException("Account not found");
        }
        // System.out.println("Matched: " +
        // passwordEncoder.matches(request.getPassword(), acc_login.getPassword()));
        // System.out.println("acc_login.getPassword: " + acc_login.getPassword());
        // System.out.println("request.getPassword: " + request.getPassword());

        // So sánh password

        if (!passwordEncoder.matches(request.getPassword(), acc_login.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        // Nếu đăng nhập thành công, tạo token và trả về cho client

        String token = jwtUtil.generateToken(acc_login.getUsername());

        return token;

    }

    @Override
    public Account register(RegisterRequest request) {
        if (accountRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        Account account = new Account();
        account.setUsername(request.getUsername());
        account.setPassword(passwordEncoder.encode(request.getPassword()));
        account.setEmail(request.getEmail());
        account.setFullname(request.getFullname());

        Department department = new Department();
        department.setId(request.getDepartmentId());
        account.setDepartment(department);

        Position position = new Position();
        position.setId(request.getPositionId());
        account.setPosition(position);

        Account accountRegister = accountRepository.save(account);

        return accountRegister;
    }


}
