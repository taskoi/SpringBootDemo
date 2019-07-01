package com.webfactory.springbootdemo.demoproject.service;

import com.webfactory.springbootdemo.demoproject.config.SecurityService;
import com.webfactory.springbootdemo.demoproject.model.Location;
import com.webfactory.springbootdemo.demoproject.model.Post;
import com.webfactory.springbootdemo.demoproject.model.Role;
import com.webfactory.springbootdemo.demoproject.model.User;
import com.webfactory.springbootdemo.demoproject.persistance.LocationRepository;
import com.webfactory.springbootdemo.demoproject.persistance.PostRepository;
import com.webfactory.springbootdemo.demoproject.persistance.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@RunWith(MockitoJUnitRunner.Silent.class)
public class SecurityServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    PostRepository postRepository;

    @Mock
    LocationRepository locationRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    SecurityService securityService;

    @BeforeAll
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void hasAccessPostTest(){
        Location location = new Location((float) 22, (float) 33, "Skopje", "Macedonia");
        location.setId(1L);
        Role role = new Role("USER");
        role.setId(1L);
        User user = new User("tivan997@hotmail.com", "tasko", "Password012301!@#\"", "asdasd", "ivan", "tasevski", location, Collections.singletonList(role));
        user.setId(1L);
        Post post = new Post("title","description",user,location);
        post.setId(1L);
        user.setUserPostsList(Collections.singletonList(post));
        Authentication authentication = new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                Collection<GrantedAuthority> list = new ArrayList<>();
                for (Role role : user.getRoles()) {
                    list.add(new SimpleGrantedAuthority(role.getRole()));
                }
                return list;
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return user.getUsername();
            }

            @Override
            public boolean isAuthenticated() {
                return false;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return user.getFirstName();
            }
        };

        Mockito.when(userRepository.findByUsername(Mockito.any())).thenReturn(user);
        boolean flag = securityService.hasAccessPost(authentication,1);
        Assert.assertTrue(flag);
        System.out.println(flag);
    }

    @Test
    public void hasAccessPostTestFalse(){
        Location location = new Location((float) 22, (float) 33, "Skopje", "Macedonia");
        location.setId(1L);
        Role role = new Role("USER");
        role.setId(1L);
        User user = new User("tivan997@hotmail.com", "tasko", "Password012301!@#\"", "asdasd", "ivan", "tasevski", location, Collections.singletonList(role));
        user.setId(1L);
        Post post = new Post("title","description",user,location);
        post.setId(1L);
        user.setUserPostsList(Collections.singletonList(post));

        User user1 = new User();
        user1.setId(2L);
        user1.setUsername("username");
        user1.setFirstName("user1");
        Role role1 = new Role("NOUSER");
        role1.setId(1L);
        Post post1 = new Post("post","post",user1,location);
        post.setId(2L);
        user1.setUserPostsList(Collections.singletonList(post1));

        Authentication authentication = new Authentication() {

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                Collection<GrantedAuthority> list = new ArrayList<>();
                for (Role role : user1.getRoles()) {
                    list.add(new SimpleGrantedAuthority(role.getRole()));
                }
                return list;
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return user1.getUsername();
            }

            @Override
            public boolean isAuthenticated() {
                return false;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return user1.getFirstName();
            }
        };

        Mockito.when(userRepository.findByUsername(Mockito.any())).thenReturn(user);
        boolean flag = securityService.hasAccessPost(authentication,1);
        Assert.assertFalse(false);
        System.out.println(flag);
    }

    @Test
    public void hasAccessUser(){
        Location location = new Location((float) 22, (float) 33, "Skopje", "Macedonia");
        location.setId(1L);
        Role role = new Role("USER");
        role.setId(1L);
        User user = new User("tivan997@hotmail.com", "tasko", "Password012301!@#\"", "asdasd", "ivan", "tasevski", location, Collections.singletonList(role));
        user.setId(1L);

        Authentication authentication = new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                Collection<GrantedAuthority> list = new ArrayList<>();
                for (Role role : user.getRoles()) {
                    list.add(new SimpleGrantedAuthority(role.getRole()));
                }
                return list;
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return user.getUsername();
            }

            @Override
            public boolean isAuthenticated() {
                return false;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return user.getFirstName();
            }
        };

        Mockito.when(userRepository.findByUsername(Mockito.any())).thenReturn(user);
        boolean flag = securityService.hasAccessUser(authentication,1L);
        System.out.println(flag);
    }

    @Test
    public void hasAccessUserFalse(){
        Location location = new Location((float) 22, (float) 33, "Skopje", "Macedonia");
        location.setId(1L);
        Role role = new Role("USER");
        role.setId(1L);
        User user = new User("tivan997@hotmail.com", "tasko", "Password012301!@#\"", "asdasd", "ivan", "tasevski", location, Collections.singletonList(role));
        user.setId(1L);
        Post post = new Post("title","description",user,location);
        post.setId(1L);
        user.setUserPostsList(Collections.singletonList(post));

        User user1 = new User();
        user1.setId(2L);
        user1.setUsername("username");
        user1.setFirstName("user1");
        Role role1 = new Role("NOUSER");
        role1.setId(1L);
        Post post1 = new Post("post","post",user1,location);
        post.setId(2L);
        user1.setUserPostsList(Collections.singletonList(post1));

        Authentication authentication = new Authentication() {

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                Collection<GrantedAuthority> list = new ArrayList<>();
                for (Role role : user1.getRoles()) {
                    list.add(new SimpleGrantedAuthority(role.getRole()));
                }
                return list;
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return user1.getUsername();
            }

            @Override
            public boolean isAuthenticated() {
                return false;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return user1.getFirstName();
            }
        };

        Mockito.when(userRepository.findByUsername(Mockito.any())).thenReturn(user);
        boolean flag = securityService.hasAccessUser(authentication,2L);
        System.out.println(flag);

        Assert.assertFalse(flag);
    }
}
