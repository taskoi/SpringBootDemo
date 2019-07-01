package com.webfactory.springbootdemo.demoproject.service.impl;

import com.webfactory.springbootdemo.demoproject.model.Post;
import com.webfactory.springbootdemo.demoproject.model.User;
import com.webfactory.springbootdemo.demoproject.persistance.LocationRepository;
import com.webfactory.springbootdemo.demoproject.persistance.RoleRepository;
import com.webfactory.springbootdemo.demoproject.persistance.UserRepository;
import com.webfactory.springbootdemo.demoproject.service.SecurityService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SecurityServiceImpl implements SecurityService {

    private final UserRepository userRepository;

    public SecurityServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    @Cacheable(value = "security",key = "#postId")
    public boolean hasAccessPost(String username, int postId) {
        User user = userRepository.findByUsername(username);
        for (Post post : user.getUserPostsList()) {
            if (post.getId() == postId) {
                return true;
            }
        }
        return false;
    }

    @Override
    @Cacheable(value = "security",key = "#userId")
    public boolean hasAccessUser(String username, Long userId) {
        User user = userRepository.findByUsername(username);
        return user.getId().equals(userId);
    }


}

