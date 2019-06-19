package com.webfactory.springbootdemo.demoproject.service;

import com.webfactory.springbootdemo.demoproject.exeptions.user.exceptions.*;
import com.webfactory.springbootdemo.demoproject.model.*;
import com.webfactory.springbootdemo.demoproject.model.reguest.bodies.UserForm;
import com.webfactory.springbootdemo.demoproject.persistance.LocationRepository;
import com.webfactory.springbootdemo.demoproject.persistance.RoleRepository;
import com.webfactory.springbootdemo.demoproject.persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.security.Principal;
import java.util.*;

@Service
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    private void checkUserForm(UserForm userForm) throws UserExistsException, NicknameNotValidException {
        checkNickName(userForm);
        checkEmail(userForm);
    }

    private void checkNickName(UserForm userForm) throws NicknameNotValidException {
        if (userRepository.findAllByNickname(userForm.getNickname()).size() > 0)
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

        return userRepository.save(actualUser);
    }

    public List<User> findAll() throws UserNotFoundException {
        List<User> all = userRepository.findAll();
        if (all.size() == 0) {
            throw new UserNotFoundException("There are no users!");
        } else {
            return userRepository.findAll();
        }
    }

    public Optional<User> findById(Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent())
            return user;
        else
            throw new UserNotFoundException("User with that id does not exist");
    }

    public void deleteUser(Long id) throws UserNotFoundException {
        if (userRepository.findById(id).isPresent())
            userRepository.deleteById(id);
        else
            throw new UserNotFoundException("User with that id does not exist");
    }

    public List<User> findByNickname(String nickname) throws UserNotFoundException {
        List<User> all = userRepository.findAllByNickname(nickname);
        if (all.size() == 0) {
            throw new UserNotFoundException("There are no users with that username");
        }
        //all.stream().filter(user -> user.getNickname().equals(nickname)).collect(Collectors.toList());
        else return all;
    }

    public List<User> findByLocationCity(String city) throws UserNotFoundException {
        List<User> all = userRepository.findAllByLocationCityContaining(city);
        if (all.size() == 0) {
            throw new UserNotFoundException("There are no users with that location city");
        }
        //  all.stream().filter(user -> user.getLocation().getCity().equals(city)).collect(Collectors.toList());
        else
            return all;
    }

    public List<User> findAllByUsername(String username) {
        List<User> all = userRepository.findAllByUsername(username);
        if (all.size() == 0)
            throw new UsernameNotFoundException("There are no users with that username");
        else
            return all;
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





