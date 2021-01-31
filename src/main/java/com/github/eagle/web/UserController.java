package com.github.eagle.web;

import com.github.eagle.domain.User;
import com.github.eagle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Edwin Wu
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/v1.1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public Object listUsers() {
        return userService.listUsers();
    }

    @GetMapping("/{id}")
    public Object getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/")
    public String saveUser(@RequestBody @Validated User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            FieldError error = (FieldError) allErrors.get(0);
            String message = error.getObjectName() + ","
                    + error.getField() + ","
                    + error.getDefaultMessage();
            return "{'status': 'error', 'message': '" + message + "'}";
        }
        userService.saveUser(user);
        return "{'status': 'success', 'message': '新增用户ID为" + user.getId() + "'}";
    }

    @PutMapping("/{id}")
    public String updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return "success";
    }

    @DeleteMapping("/{id}")
    public String removeUser(@PathVariable("id") Long id) {
        userService.removeUser(id);
        return "success";
    }
}
