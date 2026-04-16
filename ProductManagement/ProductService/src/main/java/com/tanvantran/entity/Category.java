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
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Category", catalog = "ProductManagement")
@Getter
@Setter
public class Category implements Serializable {
	@Column(name = "CategoryId")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private short id;

	@Column(name = "CategoryName", length = 30, nullable = false, unique = true)
	private String name;

	@OneToMany(mappedBy = "category")
	private List<Product> products;

}

