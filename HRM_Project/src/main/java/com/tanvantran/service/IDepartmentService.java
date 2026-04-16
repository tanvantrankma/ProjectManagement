package com.tanvantran.service;

import java.util.List;

import com.tanvantran.entity.Department;

public interface IDepartmentService {

	List<Department> getAllDepartment();

	Department getDepartmentById(Short id);

}
