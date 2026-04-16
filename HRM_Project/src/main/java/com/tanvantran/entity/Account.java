package com.tanvantran.entity;

import java.io.Serializable;
import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "Account", catalog = "TestingSystemSpring")
public class Account implements Serializable {
	
	@Column(name = "AccountID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private short id;
	
	@Column(name = "Email", length = 50, nullable = false, unique = true)
	private String email;
	
	@Column(name = "Username", length = 50, nullable = false, unique = true)
	private String username;
	
	@Column(name = "FullName", length = 50, nullable = false)
	private String fullname;
	
	@ManyToOne // Nhiều account có thể thuộc 1 phòng ban
	@JoinColumn(name = "DepartmentID", nullable = false)
	private Department department;
	
	@ManyToOne
	@JoinColumn(name = "PositionID", nullable = false)
	private Position position;
	
	@Column(name = "CreateDate")
//	@Temporal(TemporalType.TIMESTAMP) // Tương ứng với DATETIME dưới DB
	@CreationTimestamp // Tương ứng với DEFAULT NOW() dưới DB
	private Date createDate;

	public Account() {
		super();
	}

	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	

}
