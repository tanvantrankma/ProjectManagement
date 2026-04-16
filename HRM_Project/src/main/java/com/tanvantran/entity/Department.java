package com.tanvantran.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Department", catalog = "TestingSystemSpring")
public class Department implements Serializable{
	
	@Column(name = "DepartmentID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Tăng theo ID
	private short id;
	
	@Column(name = "DepartmentName", length = 30, nullable = false, unique = true)
	private String name;
	
	// Một phòng ban có thể có nhiều Account
	@OneToMany(mappedBy = "department")
	List<Account> listAccounts; 

	public Department() {
		super();
	}

	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
