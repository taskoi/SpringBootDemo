package com.webfactory.springbootdemo.demoproject.config;

import com.webfactory.springbootdemo.demoproject.model.Post;
import com.webfactory.springbootdemo.demoproject.model.User;
import com.webfactory.springbootdemo.demoproject.persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class SecurityService {

    private final
    UserRepository userRepository;

    public SecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean hasAccessPost(Authentication authentication, int id) {
        System.out.println("da" + authentication.getPrincipal().toString());
        String username = authentication.getPrincipal().toString();
        User user = userRepository.findByUsername(username);
        boolean flag = false;
        for (Post post : user.getUserPostsList()) {
            if (post.getUser().getId() == user.getId()) {
                flag= true;
            }
        }
        return flag;
    }


    public boolean hasAccessUser(Authentication authentication, Long id ){
        String username = authentication.getPrincipal().toString();
        Optional<User> user = userRepository.findById(id);
        boolean flag = false;
        if(user.isPresent()){
            User user1 = userRepository.findByUsername(username);
            if(user1.getId() == user.get().getId()){
                flag = true;
            }
        }
        return flag;
    }
}

