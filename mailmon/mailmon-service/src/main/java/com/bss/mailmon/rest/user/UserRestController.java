package com.bss.mailmon.rest.user;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bss.mailmon.service.exceptions.NoContentException;
import com.bss.mailmon.service.user.User;
import com.bss.mailmon.service.user.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserRestController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{id:\\d+}", method = GET)
    public User get(@PathVariable(value = "id") final Long id) throws NoContentException {
        User user = userService.get(id);
        if (user == null) {
            throw new NoContentException();
        }
        return user;
    }

    @RequestMapping(value = "/", method = POST)
    public User create(@RequestBody final User user) {
        return userService.create(user);
    }

    @RequestMapping(value = "/{id:\\d+}", method = PUT)
    public User update(@RequestBody final User user) {
        return userService.update(user);
    }

    @RequestMapping(value = "/{id:\\d+}", method = DELETE)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
