package com.bss.mailmon.rest.user;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import javax.annotation.Resource;

import ma.glasnost.orika.MapperFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bss.mailmon.rest.exceptions.NotFoundException;
import com.bss.mailmon.service.user.User;
import com.bss.mailmon.service.user.UserService;
import com.bss.mailmon.user.UserEntity;

@RestController
@RequestMapping(name = "/users")
public class UserRestController {
	@Autowired
	private UserService userService;

	@Resource(name = "userMapperFull")
	private MapperFacade userMapper;

	@RequestMapping(name = "/{id:\\d+}", method = GET)
	public User get(@PathVariable final Long id) {
		UserEntity userEntity = userService.get(id);
		if (userEntity == null) {
			throw new NotFoundException();
		}
		return userMapper.map(userEntity, User.class);
	}

	@RequestMapping(name = "/", method = POST)
	public User create(final User user) {
		UserEntity userEntity = userMapper.map(user, UserEntity.class);
		userEntity = userService.create(userEntity);
		return userMapper.map(userEntity, User.class);
	}

	@RequestMapping(name = "/{id:\\d+}", method = PUT)
	public User update(final User user) {
		UserEntity userEntity = userMapper.map(user, UserEntity.class);
		userEntity = userService.update(userEntity);
		return userMapper.map(userEntity, User.class);
	}

	@RequestMapping(name = "/{id:\\d+}", method = DELETE)
	public void delete(@PathVariable Long id) {
		userService.delete(id);
	}
}
