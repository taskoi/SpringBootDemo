package com.webfactory.springbootdemo.demoproject.web;

import com.webfactory.springbootdemo.demoproject.exeptions.*;
import com.webfactory.springbootdemo.demoproject.model.User;
import com.webfactory.springbootdemo.demoproject.model.reguest.bodies.UserForm;
import com.webfactory.springbootdemo.demoproject.service.PostService;
import com.webfactory.springbootdemo.demoproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@Transactional(propagation = Propagation.REQUIRED)
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    //OAUTH
    @RequestMapping("user")
    @ResponseBody
    public Principal user(Principal principal){
        return principal;
    }

    @PostMapping("/createUser")
    public User createUser(@Valid @RequestBody UserForm userForm) throws UserMissingParameterException, EmailNotValidException, LocationMissingParameterException, PasswordNotValidException {
        return userService.createUser(userForm);
    }

    @PreAuthorize("#oauth2.hasScope('write')")
    @PatchMapping("/updateUser/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody UserForm userForm) throws UserNotFoundException, UserMissingParameterException, LocationMissingParameterException, EmailNotValidException, PasswordNotValidException {
        return userService.updateUser(userForm, id);
    }

    @PreAuthorize("#oauth2.hasScope('read')")
    @GetMapping("/findAll")
    public List<User> findAll() {
        System.out.println("findAll");
        return userService.findAll();
    }

    @PreAuthorize("#oauth2.hasScope('read')")
    @GetMapping("/findById/{id}")
    public Optional<User> findUser(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PreAuthorize("#oauth2.hasScope('write')")
    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
