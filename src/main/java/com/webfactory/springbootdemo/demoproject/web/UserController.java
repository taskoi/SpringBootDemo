package com.webfactory.springbootdemo.demoproject.web;

import com.webfactory.springbootdemo.demoproject.exeptions.user.exceptions.*;
import com.webfactory.springbootdemo.demoproject.model.User;
import com.webfactory.springbootdemo.demoproject.model.reguest.bodies.UserForm;
import com.webfactory.springbootdemo.demoproject.service.UserService;
import com.webfactory.springbootdemo.demoproject.service.impl.UserServiceImpl;
import io.swagger.annotations.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Optional;

@RestController
@Transactional(propagation = Propagation.REQUIRED)
@RequestMapping("/api/user")
@Api(value = "demoproject")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Create an user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created user"),
            @ApiResponse(code = 401, message = "You are not authorized to use this resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping("/createUser")
    public User createUser(@Valid @RequestBody UserForm userForm) throws UserExistsException, NicknameNotValidException {
        return userService.createUser(userForm);
    }

    @ApiOperation(value = "Update an user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated user"),
            @ApiResponse(code = 401, message = "You are not authorized to use this resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PreAuthorize("#oauth2.hasScope('write')")
    @PatchMapping("/updateUser/{id}")
    public User updateUser(@PathVariable Long id, @Valid @RequestBody UserForm userForm) throws UserNotFoundException, NicknameNotValidException {
        return userService.updateUser(userForm, id);
    }

    @ApiOperation(value = "View a list of all users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrived list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PreAuthorize("#oauth2.hasScope('read')")
    @GetMapping("/findAll")
    public Page<User> findAll(Pageable pageable) throws UserNotFoundException {
        return userService.findAll(pageable);
    }

    @ApiOperation(value = "Search for a user by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrived user"),
            @ApiResponse(code = 401, message = "You are not authorized to use this resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PreAuthorize("#oauth2.hasScope('read')")
    @GetMapping("/findById/{id}")
    public Optional<User> findUser(@PathVariable Long id) throws UserNotFoundException {
        return userService.findById(id);
    }

    @ApiOperation(value = "Delete a user by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted user"),
            @ApiResponse(code = 401, message = "You are not authorized to use this resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PreAuthorize("#oauth2.hasScope('write')")
    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable Long id) throws UserNotFoundException {
        userService.deleteUser(id);
    }
}
