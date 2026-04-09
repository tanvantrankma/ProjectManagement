package com.tanvantran.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tanvantran.entity.Product;

public interface IProductRepository extends JpaRepository<Product, Short> {

}

