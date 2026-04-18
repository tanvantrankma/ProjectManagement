package com.tanvantran.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


import com.tanvantran.service.IAccountService;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfiguration {


	@Autowired
	private IAccountService accountService;


	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()).cors(cors -> {
		}).authorizeHttpRequests(auth -> 
		auth
		// Cho phép gọi login không cần xác thực
        .requestMatchers("/api/v1/login/**").permitAll()
        
        // Cấu hình cho Account
        // Chỉ ADMIN được phép tạo/sửa/xoá account
        .requestMatchers(HttpMethod.POST,   "/api/v1/accounts/**").hasRole("ADMIN")
        .requestMatchers(HttpMethod.PUT,    "/api/v1/accounts/**").hasRole("ADMIN")
        .requestMatchers(HttpMethod.DELETE, "/api/v1/accounts/**").hasRole("ADMIN")

        // ADMIN & USER đều xem được danh sách / chi tiết
        .requestMatchers(HttpMethod.GET, "/api/v1/accounts/**").hasAnyRole("ADMIN", "USER")
        
        //-- Cấu hình cho Department
        
        // Chỉ ADMIN được phép tạo/sửa/xoá account
        .requestMatchers(HttpMethod.POST,   "/api/v1/departments/**").hasRole("ADMIN")
        .requestMatchers(HttpMethod.PUT,    "/api/v1/departments/**").hasRole("ADMIN")
        .requestMatchers(HttpMethod.DELETE, "/api/v1/departments/**").hasRole("ADMIN")

        // ADMIN & USER đều xem được danh sách / chi tiết
        .requestMatchers(HttpMethod.GET, "/api/v1/departments/**").hasAnyRole("ADMIN", "USER")
        
        //-- Cấu hình cho Position
        
        // Chỉ ADMIN được phép tạo/sửa/xoá account
        .requestMatchers(HttpMethod.POST,   "/api/v1/positions/**").hasRole("ADMIN")
        .requestMatchers(HttpMethod.PUT,    "/api/v1/positions/**").hasRole("ADMIN")
        .requestMatchers(HttpMethod.DELETE, "/api/v1/positions/**").hasRole("ADMIN")

        // ADMIN & USER đều xem được danh sách / chi tiết
        .requestMatchers(HttpMethod.GET, "/api/v1/positions/**").hasAnyRole("ADMIN", "USER")
        
        // Các request khác: chỉ cần authenticated
        .anyRequest().authenticated()).httpBasic(Customizer.withDefaults());

		return http.build();
	}


	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(accountService);
//		authProvider.setUserDetailsService(accountService);
		authProvider.setPasswordEncoder(new BCryptPasswordEncoder());
		return authProvider;
	}


	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
}
