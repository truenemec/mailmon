package com.bss.mailmon.config;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bss.mailmon.service.user.User;
import com.bss.mailmon.user.UserEntity;

@Configuration
public class MapperConfig {

	@Bean(name = "userMapperFull")
	public MapperFacade userMapper() {
		MapperFactory factory = new DefaultMapperFactory.Builder().build();
		factory.registerClassMap(factory.classMap(User.class, UserEntity.class)
				.toClassMap());
		return factory.getMapperFacade();
	}
}
