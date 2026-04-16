package com.tanvantran.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Product", catalog = "ProductManagement")
@Getter
@Setter
public class Product implements Serializable {
	@Column(name = "ProductId")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private short id;

	@Column(name = "ProductName", length = 50, nullable = false, unique = true)
	private String name;

	@Column(name = "ProductPrice", length = 50, nullable = false)
	private String price;

	@Column(name = "ProductInfo", length = 200, nullable = false)
	private String info;

	@Column(name = "ProductDetail", length = 500)
	private String detail;

	@Column(name = "RatingStar")
	private short ratingStar;

	@Column(name = "ProductImageName", length = 50, nullable = false)
	private String imageName;

	@ManyToOne
	@JoinColumn(name = "ManufacturerId", nullable = false)
	private Manufacturer manufacturer;

	@ManyToOne
	@JoinColumn(name = "CategoryId", nullable = false)
	private Category category;

}
