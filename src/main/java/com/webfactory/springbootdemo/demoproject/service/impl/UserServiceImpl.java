package com.webfactory.springbootdemo.demoproject.service.impl;

import com.webfactory.springbootdemo.demoproject.events.*;
import com.webfactory.springbootdemo.demoproject.exeptions.user.exceptions.*;
import com.webfactory.springbootdemo.demoproject.model.*;
import com.webfactory.springbootdemo.demoproject.model.reguest.bodies.UserForm;
import com.webfactory.springbootdemo.demoproject.persistance.LocationRepository;
import com.webfactory.springbootdemo.demoproject.persistance.LogRepository;
import com.webfactory.springbootdemo.demoproject.persistance.RoleRepository;
import com.webfactory.springbootdemo.demoproject.persistance.UserRepository;


import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserDetailsService, com.webfactory.springbootdemo.demoproject.service.UserService {

    private final UserRepository userRepository;
    private final LocationRepository locationRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher applicationEventPublisher;

    public UserServiceImpl(UserRepository userRepository, LocationRepository locationRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, LogRepository logRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    //home config
//    @PostConstruct
//    public void initUser(){
//        User user = new User();
//        Location location = new Location((float)22,(float)33,"Skopje","Makedonija");
//        Role role = new Role("USER");
//        user.setUsername("tasko");
//        user.setRoles(Arrays.asList(role));
//        user.setLocation(location);
//        user.setPassword(passwordEncoder.encode("Password123!@#"));
//        user.setNickname("tasko");
//        user.setLastName("Tasevski");
//        user.setFirstName("Ivan");
//        user.setEmail("tivan997@hotmail.com");
//
//        locationRepository.save(location);
//        userRepository.save(user);
//    }
    private void checkUserForm(UserForm userForm) throws UserExistsException, NicknameNotValidException {
        checkNickName(userForm);
        checkEmail(userForm);
    }

    private void checkNickName(UserForm userForm) throws NicknameNotValidException {
        if (userRepository.findByNickname(userForm.getNickname()) != null)
            throw new NicknameNotValidException(userForm.getNickname());
    }


    private void checkEmail(UserForm userForm) throws UserExistsException {
        if (userRepository.findByEmail(userForm.getEmail()) != null)
            throw new UserExistsException(userForm.getEmail());
    }

    public User createUser(UserForm userForm) throws UserExistsException, NicknameNotValidException {

        User user = new User();
        Location location = new Location();
        List<Role> roles = new ArrayList<>();

        checkUserForm(userForm);

        user.setEmail(userForm.getEmail());
        user.setFirstName(userForm.getFirstName());
        user.setLastName(userForm.getLastName());
        user.setPassword(passwordEncoder.encode(userForm.getPassword()));
        user.setNickname(userForm.getNickname());
        user.setUsername(userForm.getUsername());
        location.setCity(userForm.getLocation().getCity());
        location.setCountry(userForm.getLocation().getCountry());
        location.setLatitude(userForm.getLocation().getLatitude());
        location.setLongitude(userForm.getLocation().getLongitude());
        for (Role r : userForm.getRoles()) {
            roles.add(r);
            roleRepository.save(r);
        }

        user.setRoles(roles);
        user.setLocation(location);
        location.getLocationUsers().add(user);

        locationRepository.save(location);

        applicationEventPublisher.publishEvent(new CreateUserEvent(this, user));

        return userRepository.save(user);
    }


    public User updateUser(UserForm userForm, Long id) throws UserNotFoundException, NicknameNotValidException {

        Optional<User> user = userRepository.findById(id);
        User actualUser;

        if (user.isPresent())
            actualUser = user.get();
        else
            throw new UserNotFoundException(userForm.getNickname());

        if (userForm.getPassword() != null) {
            actualUser.setPassword(userForm.getPassword());
        }

        if (userForm.getLastName() != null) {
            actualUser.setLastName(userForm.getLastName());
        }

        if (userForm.getFirstName() != null) {
            actualUser.setFirstName(userForm.getFirstName());
        }

        if (userForm.getLocation() != null) {
            actualUser.setLocation(userForm.getLocation());
        }

        locationRepository.save(actualUser.getLocation());

        if (userForm.getUsername() != null) {
            actualUser.setUsername(userForm.getUsername());
        }

        if (userForm.getNickname() != null) {
            checkNickName(userForm);
            actualUser.setNickname(userForm.getNickname());
        }

        //change was done because in testing was throwing UnsuportedOperationException
        //because i was returing fixed size of an array
        if (userForm.getRoles() != null) {
            for (Role r : userForm.getRoles()) {
                List<Role> userRoles = new LinkedList<>(actualUser.getRoles());
                userRoles.add(r);
                actualUser.setRoles(userRoles);
                roleRepository.save(r);
            }
        }

        applicationEventPublisher.publishEvent(new UpdateUserEvent(this, actualUser));
        return userRepository.save(actualUser);
    }

    public Optional<User> findById(Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            applicationEventPublisher.publishEvent(new FindUserByIdEvent(this, user.get()));

            return user;
        } else
            throw new UserNotFoundException("User with that id does not exist");
    }

    public void deleteUser(Long id) throws UserNotFoundException {
        if (userRepository.findById(id).isPresent()) {
            applicationEventPublisher.publishEvent(new DeleteUserEvent(this, id));

            userRepository.deleteById(id);
        } else
            throw new UserNotFoundException("User with that id does not exist");
    }

    public Page<User> findAll(Pageable pageable) throws UserNotFoundException {
        Page<User> all = userRepository.findAll(pageable);
        if (all.getSize() == 0) {
            throw new UserNotFoundException("There are no users!");
        } else {
            List<User> users = all.getContent();
            applicationEventPublisher.publishEvent(new FindAllUsersEvent(this, users));
            return all;
        }
    }
    public Page<User> findByNickname(Pageable pageable, String nickname) throws UserNotFoundException {
        Page<User> all = userRepository.findAllByNickname(pageable, nickname);
        if (all.getSize() == 0) {
            throw new UserNotFoundException("There are no users with that username");
        } else {
            List<User> users = all.getContent();

            applicationEventPublisher.publishEvent(new FindAllUsersByNicknameEvent(this, users));
            return all;
        }
    }

    public Page<User> findByLocationCity(Pageable pageable, String city) throws UserNotFoundException {
        Page<User> all = userRepository.findAllByLocationCityContaining(pageable, city);
        if (all.getSize() == 0) {
            throw new UserNotFoundException("There are no users with that location city");
        } else {
            List<User> list = all.getContent();
            applicationEventPublisher.publishEvent(new FindAllUsersByLocationCityEvent(this, list));

            return all;
        }
    }

    public Page<User> findAllByUsername(Pageable pageable, String username) {
        Page<User> all = userRepository.findAllByUsername(pageable, username);
        if (all.getSize() == 0)
            throw new UsernameNotFoundException("There are no users with that username");
        else {
            List<User> users = all.getContent();
            applicationEventPublisher.publishEvent(new FindAllUsersByUsernameEvent(this, users));

            return all;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("User not found");
        else
            return new UserDetailsImpl(user);
    }
}





