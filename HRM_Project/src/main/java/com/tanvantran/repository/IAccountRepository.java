package com.tanvantran.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.tanvantran.entity.Account;
import com.tanvantran.entity.Department;

import java.util.List;
import java.util.Optional;


public interface IAccountRepository extends JpaRepository<Account, Short>, JpaSpecificationExecutor<Account> {
	
	// Khai báo hàm tìm Account theo Username/ Optional không ra giá trị null nghĩa là hoặc có account hoặc empty
	Optional<Account> findByUsername(String username);
	
	// Khai báo hàm tìm Account theo Email
	Optional<Account> findByEmail(String email);
	
	Account getByUsername(String username);
	
	Account getByEmail(String email);
	
	// Kiểm tra sự tồn tại của username hoặc email
	boolean existsByUsernameOrEmail(String username, String email);
	
	// Tìm kiếm tốp 1 theo Id và sắp xếp theo DESC
	Account findTopByOrderByIdDesc();
	
	// Tìm 5 record được tạo ra gần nhất
	Account findTop5ByOrderByCreateDateDesc();
	
	// Đếm số lượng Account theo DepartmentId
	int countByDepartment(Department department);
	
	// Tìm Account mới nhất  được tạo
	//	Account findTopByOrderByIdDesc();
	
	// Tìm Account trong khoảng id: idFrom = 3, idTo = 6
	List<Account> findByIdBetween(short idFrom, short idTo);
	
	// JPQL
	// Lấy danh sách Username đang có trên hệ thống (lấy trực tiếp)
	@Query("select a.username from Account a")
	List<String> getAllUsernames();
	
	// JPQL
	// Tìm danh sách Account theo id phòng ban truyền vào departmentId
	@Query("select a from Account a where a.department.id = :depIdPram")
	List<Account> getAccountByIdDepartment(@Param("depIdPram") short departmentId);
	
	// NativeQuery
	// Tìm danh sách Account theo tên của Position : Dev
	@Query(value = """
			SELECT a.* FROM `Account` a INNER JOIN `Position` p
			ON a.PositionID = p.PositionID
			WHERE p.PositionName = :posNameParam;
			""", nativeQuery = true)
	List<Account> getAccountByPosition(@Param("posNameParam") String positionName);
}
