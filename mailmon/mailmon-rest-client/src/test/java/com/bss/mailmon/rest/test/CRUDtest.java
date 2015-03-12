package com.bss.mailmon.rest.test;

import org.testng.annotations.Test;

import com.bss.mailmon.rest.user.UserController;
import com.bss.mailmon.service.exceptions.NoContentException;
import com.bss.mailmon.service.exceptions.ServiceException;
import com.bss.mailmon.service.user.User;

public class CRUDtest {
    @Test
    public void crud() throws ServiceException, NoContentException {
        UserController userController = new UserController();
        final User user = new User();
        user.setFirstname("first");
        user.setLastname("last");
        user.setMiddlename("middle");
        user.setLogin("timofeev");
        Long id = userController.create(user);
        User userTmp = userController.get(id);
        assert userTmp.equals(user);
        userTmp.setFirstname("first2");
        userController.update(userTmp);
        User userTmp2 = userController.get(id);
        assert userTmp2.equals(userTmp);
        userController.delete(id);
        boolean notFound = false;
        try {
            userController.get(id);
        } catch (NoContentException e) {
            notFound = true;
        }
        assert notFound == true;

    }
}
