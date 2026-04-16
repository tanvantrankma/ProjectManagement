package com.tanvantran.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tanvantran.entity.Department;
import com.tanvantran.repository.IDepartmentRepository;

@Service
public class DepartmentService implements IDepartmentService {
	
	@Autowired
	private IDepartmentRepository departmentRepository;

	@Override
	public List<Department> getAllDepartment() {
		// TODO Auto-generated method stub
		return departmentRepository.findAll();
	}

	@Override
	public Department getDepartmentById(Short id) {
		// TODO Auto-generated method stub
		return departmentRepository.getById(id);
	}

}
