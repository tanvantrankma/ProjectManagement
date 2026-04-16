package com.tanvantran.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanvantran.dto.DepartmentDto;
import com.tanvantran.entity.Department;
import com.tanvantran.service.IDepartmentService;

@RestController
@RequestMapping(value = "api/v1/departments")
@CrossOrigin("*")
public class DepartmentController {
	
	@Autowired  //Khởi tạo intan
	private IDepartmentService departmentService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping()
	public ResponseEntity<?> getAllDepartment() {
		List<Department> listDepartments = departmentService.getAllDepartment();
		
		List<DepartmentDto> listDepartmentDtos = new ArrayList<>();
		for (Department department : listDepartments) {
//			DepartmentDto departmentDto = new DepartmentDto();
//			departmentDto.setId(department.getId());
//			departmentDto.setName(department.getName());
			
			DepartmentDto departmentDto = modelMapper.map(department, DepartmentDto.class);
			
			listDepartmentDtos.add(departmentDto);
		}
		return new ResponseEntity<>(listDepartmentDtos, HttpStatus.OK);
	}
	
	// getDepartmentById
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getDepartmentById(@PathVariable(name = "id") Short id) {
		Department department = departmentService.getDepartmentById(id);
		
		DepartmentDto departmentDto = new DepartmentDto();
		departmentDto.setId(department.getId());
		departmentDto.setName(department.getName());
		
		return new ResponseEntity<>(departmentDto, HttpStatus.OK);
	}
}
