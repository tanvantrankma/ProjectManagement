package com.vti.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.PossitonDto;
import com.vti.entity.Department;
import com.vti.entity.Position;
import com.vti.service.IPossitionService;

@RestController
@RequestMapping(value = "api/v1/possitions")
@CrossOrigin("*")
public class PossitionController {

    @Autowired
    private IPossitionService possitionService;

    @GetMapping()
    public ResponseEntity<?> getAllPossitons() {
        List<Position> entities = possitionService.getAllPossition();

        List<PossitonDto> dtos = new ArrayList<>();

        for (Position entity : entities) {
            PossitonDto dto = new PossitonDto(entity.getId(), entity.getName().toString());
            dtos.add(dto);
        }

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    // Lấy Position theo ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getPositionByID(@PathVariable(name = "id") short id) {
        Position position = possitionService.getPositonByID(id);

        PossitonDto possitonDto = new PossitonDto();
        possitonDto.setId(position.getId());
        possitonDto.setName(position.getName().toString());

        return new ResponseEntity<>(possitonDto, HttpStatus.OK);
    }
}
