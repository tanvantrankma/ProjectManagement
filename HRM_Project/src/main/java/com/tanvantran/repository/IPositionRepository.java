package com.tanvantran.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tanvantran.entity.Position;

public interface IPositionRepository extends JpaRepository<Position, Short> {

	List<Position> findAll();

}
