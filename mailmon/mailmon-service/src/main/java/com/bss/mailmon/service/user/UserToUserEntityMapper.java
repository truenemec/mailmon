package com.bss.mailmon.service.user;

import org.springframework.stereotype.Component;

import com.bss.mailmon.mapper.OrikaTransformer;
import com.bss.mailmon.user.UserEntity;

@Component
public class UserToUserEntityMapper extends OrikaTransformer<UserEntity, User> {
    public UserToUserEntityMapper() {
        super(UserEntity.class, User.class);
    }
}
