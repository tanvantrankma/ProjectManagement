package com.tanvantran.config.modelmapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tanvantran.dto.AccountDto;
import com.tanvantran.entity.Account;

@Configuration
public class ModelMapperConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
	      .setMatchingStrategy(MatchingStrategies.STRICT);
		
		modelMapper.addMappings(new PropertyMap<Account, AccountDto>() {

			@Override
			protected void configure() {
				// Chỉ để nhận biết cần mapping như thế nào
				map().setDepartment(source.getDepartment().getName());
//				map().setPosition(source.getPosition().getName().toString());
				
			}
		});

		return modelMapper;
	}
}
