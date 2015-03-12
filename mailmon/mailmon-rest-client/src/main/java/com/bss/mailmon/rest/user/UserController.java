package com.bss.mailmon.rest.user;

import com.bss.mailmon.rest.AbstractController;
import com.bss.mailmon.service.exceptions.NoContentException;
import com.bss.mailmon.service.exceptions.ServiceException;
import com.bss.mailmon.service.user.User;

public class UserController extends AbstractController {
    public User get(Long id) throws ServiceException, NoContentException {
        return requestGet(User.class, "/users/" + id);
    }

    public Long create(User user) throws ServiceException {
        return requestPost(user, "/users/", true);
    }

    public void update(User user) throws ServiceException {
        requestPut(user, "/users/" + user.getId());
    }

    public void delete(Long id) throws ServiceException {
        requestDelete("/users/" + id);
    }
}
