package com.webfactory.springbootdemo.demoproject.service;

import com.webfactory.springbootdemo.demoproject.exeptions.user.exceptions.UserExistsException;
import com.webfactory.springbootdemo.demoproject.exeptions.user.exceptions.UserNotFoundException;
import com.webfactory.springbootdemo.demoproject.model.User;
import com.webfactory.springbootdemo.demoproject.model.reguest.bodies.UserForm;
import com.webfactory.springbootdemo.demoproject.model.reguest.bodies.UserModify;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(UserForm userForm) throws UserExistsException;

    User updateUser(UserModify userModify, Long id) throws UserNotFoundException;

    Page<User> findAll(Pageable pageable) throws UserNotFoundException;

    Optional<User> findById(Long id) throws UserNotFoundException;

    void deleteUser(Long id) throws UserNotFoundException;

    Page<User> findByNickname(Pageable p,String nickname) throws UserNotFoundException;

    Page<User> findByLocationCity(Pageable pageable, String city) throws UserNotFoundException;

    Page<User> findAllByUsername(Pageable pageable, String username);
}
