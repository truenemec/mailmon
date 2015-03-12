package com.bss.mailmon.service.user;


public interface UserService {
    User get(final Long id);

    User create(final User user);

    User update(final User user);

    void delete(final Long id);
}
