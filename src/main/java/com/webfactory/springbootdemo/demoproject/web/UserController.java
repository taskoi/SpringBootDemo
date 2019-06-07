package com.webfactory.springbootdemo.demoproject.web;

import com.webfactory.springbootdemo.demoproject.exeptions.user.exceptions.*;
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

    @PostMapping("/createUser")
    public User createUser(@Valid @RequestBody UserForm userForm,Principal principal) throws UserMissingParameterException, EmailNotValidException, LocationMissingParameterException, PasswordNotValidException, LocationParameterOutOfBoundException, UserExistsException, UserParameterOutOfBoundException, NicknameNotValidException {
        return userService.createUser(userForm,principal);
    }

   @PreAuthorize("#oauth2.hasScope('write')")
    @PatchMapping("/updateUser/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody UserForm userForm) throws UserNotFoundException, UserMissingParameterException, LocationMissingParameterException, EmailNotValidException, PasswordNotValidException, LocationParameterOutOfBoundException, NicknameNotValidException, UserParameterOutOfBoundException {
        return userService.updateUser(userForm, id);
    }

    @PreAuthorize("#oauth2.hasScope('read')")
    @GetMapping("/findAll")
    public List<User> findAll() throws UserNotFoundException {
        System.out.println("findAll");
        return userService.findAll();
    }

    @PreAuthorize("#oauth2.hasScope('read')")
    @GetMapping("/findById/{id}")
    public Optional<User> findUser(@PathVariable Long id) throws UserNotFoundException {
        return userService.findById(id);
    }

    @PreAuthorize("#oauth2.hasScope('write')")
    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable Long id) throws UserNotFoundException {
        userService.deleteUser(id);
    }
}
