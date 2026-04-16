package com.tanvantran.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tanvantran.entity.Department;

public interface IDepartmentRepository extends JpaRepository<Department, Short>{

	List<Department> findAll();

}
