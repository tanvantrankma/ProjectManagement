package com.tanvantran.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Position", catalog = "TestingSystemSpring")
public class Position implements Serializable{
	
	@Column(name = "PositionID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private short id;
	
	@Column(name = "PositionName", nullable = false, unique = true)
	@Enumerated(EnumType.STRING) // Chuyển đổi 1-1 trong Java và dưới DB
	private PositionName name;
	
	@OneToMany(mappedBy = "position")
	List<Account> listAccounts;

	public Position() {
		super();
	}

	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public PositionName getName() {
		return name;
	}

	public void setName(PositionName name) {
		this.name = name;
	}
	
}
