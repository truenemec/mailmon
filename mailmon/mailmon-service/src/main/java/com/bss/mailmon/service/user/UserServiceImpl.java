package com.bss.mailmon.service.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bss.mailmon.user.UserEntity;
import com.bss.mailmon.user.UserRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {
    Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserToUserEntityMapper userToUserEntityMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public User get(Long id) {
        return userToUserEntityMapper.asDTO(userRepository.findOne(id));
    }

    @Override
    public User create(User user) {
        LOGGER.info(user.toString());
        UserEntity userEntity = userToUserEntityMapper.asEntity(user);
        return userToUserEntityMapper.asDTO(userRepository.save(userEntity));
    }

    @Override
    public User update(User user) {
        UserEntity userEntity = userToUserEntityMapper.asEntity(user);
        return userToUserEntityMapper.asDTO(userRepository.save(userEntity));
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }

}
