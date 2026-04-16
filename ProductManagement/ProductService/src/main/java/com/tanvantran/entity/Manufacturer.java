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
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Manufacturer", catalog = "ProductManagement")
@Getter
@Setter
public class Manufacturer implements Serializable {
	@Column(name = "ManufacturerId")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private short id;

	@Column(name = "ManufacturerName", nullable = false, unique = true)
	@Enumerated(EnumType.STRING)
	private ManufacturerName name;

	@OneToMany(mappedBy = "manufacturer")
	private List<Product> products;

}
