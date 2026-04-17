package com.tanvantran.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanvantran.dto.PositionDto;
import com.tanvantran.entity.Position;
import com.tanvantran.service.IPositionService;
import com.tanvantran.service.PositionService;

@RestController
@RequestMapping(value = "api/v1/positions")
@CrossOrigin("*")
public class PositionController {
	
	@Autowired
	private IPositionService positionService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping()
	public ResponseEntity<?> getAllPosition() {
		List<Position> listPositions = positionService.getAllPosition();
		
//		List<PositionDto> listPositionDtos = new ArrayList<>();
//		for (Position position : listPositions) {
//			PositionDto positionDto = new PositionDto();
//			positionDto.setId(position.getId());
//			positionDto.setName(position.getName().toString());
//			listPositionDtos.add(positionDto);
//		}
		
		List<PositionDto> listPositionDtos = listPositions.stream().map(position -> {
			PositionDto positionDto = modelMapper.map(position, PositionDto.class);
			positionDto.setName(position.getName().toString());
			return positionDto;
		}).toList();
		
		return new ResponseEntity<>(listPositionDtos, HttpStatus.OK);
	}
	

}
