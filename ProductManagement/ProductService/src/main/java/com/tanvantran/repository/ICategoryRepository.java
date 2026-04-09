package com.tanvantran.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tanvantran.entity.Category;

public interface ICategoryRepository extends JpaRepository<Category, Short> {

}

