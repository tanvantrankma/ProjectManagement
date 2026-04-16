package com.tanvantran.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tanvantran.entity.Position;
import com.tanvantran.repository.IPositionRepository;

@Service
public class PositionService implements IPositionService{
	
	@Autowired
	private IPositionRepository positionRepository;

	@Override
	public List<Position> getAllPosition() {
		// TODO Auto-generated method stub
		return positionRepository.findAll();
	}

}
