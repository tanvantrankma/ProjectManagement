package com.tanvantran.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tanvantran.entity.Manufacturer;

public interface IManufacturerRepository extends JpaRepository<Manufacturer, Short> {

}

