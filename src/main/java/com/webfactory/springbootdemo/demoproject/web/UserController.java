package com.webfactory.springbootdemo.demoproject.web;

import com.webfactory.springbootdemo.demoproject.exeptions.UserMissingParametarException;
import com.webfactory.springbootdemo.demoproject.exeptions.UserNotFoundException;
import com.webfactory.springbootdemo.demoproject.model.User;
import com.webfactory.springbootdemo.demoproject.model.UserForm;
import com.webfactory.springbootdemo.demoproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@Transactional(propagation =  Propagation.REQUIRED)
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/createUser")
    public User createUser(@Valid @RequestBody UserForm userForm, HttpServletResponse response) throws UserMissingParametarException {
        User userNew = userService.createUser(userForm);
        response.setHeader("Location", "/users/" + userForm.getId());
        return userNew;
    }

    @PatchMapping("/updateUser/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody UserForm userForm) throws UserNotFoundException, UserMissingParametarException {
        return userService.updateUser(userForm,id);
    }

    @GetMapping("/findAll")
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("/findById/{id}")
    public Optional<User> findUser(@PathVariable Long id){
        return userService.findById(id);
    }
}
