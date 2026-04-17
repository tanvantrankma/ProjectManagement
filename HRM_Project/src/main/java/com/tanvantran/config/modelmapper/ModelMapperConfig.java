package com.tanvantran.config.modelmapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tanvantran.dto.AccountDto;
import com.tanvantran.dto.DepartmentDto;
import com.tanvantran.dto.PositionDto;
import com.tanvantran.entity.Account;
import com.tanvantran.entity.Department;
import com.tanvantran.entity.Position;

@Configuration
public class ModelMapperConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
	      .setMatchingStrategy(MatchingStrategies.STRICT);
		// Cấu hình chuyển đổi cho Account
		modelMapper.addMappings(new PropertyMap<Account, AccountDto>() {

			@Override
			protected void configure() {
				// Chỉ để nhận biết cần mapping như thế nào
				map().setDepartment(source.getDepartment().getName());
//				map().setPosition(source.getPosition().getName().toString());
				
			}
		});
		
		// Cấu hình chuyển đổi cho Position
		modelMapper.addMappings(new PropertyMap<Position, PositionDto>() {

			@Override
			protected void configure() {
				// TODO Auto-generated method stub
				
			}
		});

		return modelMapper;
	}
}
