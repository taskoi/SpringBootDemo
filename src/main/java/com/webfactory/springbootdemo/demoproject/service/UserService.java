package com.webfactory.springbootdemo.demoproject.service;

import com.webfactory.springbootdemo.demoproject.exeptions.*;
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

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

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

    private void checkUserForm(UserForm userForm) throws UserMissingParameterException, EmailNotValidException, PasswordNotValidException, LocationMissingParameterException {
        if (userForm.getEmail().equals(""))
            throw new UserMissingParameterException("Missing parameter email");
        if (!validEmail(userForm.getEmail()))
            throw new EmailNotValidException("Email not valid");
        if (userForm.getFirstName().equals(""))
            throw new UserMissingParameterException("Missing parameter first name");
        if (userForm.getLastName().equals(""))
            throw new UserMissingParameterException("Missing parameter last name");
        if (userForm.getPassword().equals(""))
            throw new UserMissingParameterException("Missing parameter password");
        if (userForm.getNickname().equals(""))
            throw new UserMissingParameterException("Missing parameter nickname");
        if (userForm.getUsername().equals(""))
            throw new UserMissingParameterException("Missing parameter username");
        if (userForm.getLocation() == null)
            throw new UserMissingParameterException("Missing parameter location");
        if (userForm.getLocation().getCity().equals(""))
            throw new LocationMissingParameterException("Missing parameter city");
        else if (userForm.getLocation().getCountry().equals(""))
            throw new LocationMissingParameterException("Missing parameter country");
        else if (userForm.getLocation().getLatitude().equals(""))
            throw new LocationMissingParameterException("Missing parameter latitude");
        else if (userForm.getLocation().getLongitude().equals(""))
            throw new LocationMissingParameterException("Missing parameter longitude");
        if (userForm.getRoles() == null)
            throw new UserMissingParameterException("Missing parameter user's roles");
    }

    private boolean validEmail(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }


    public User createUser(UserForm userForm) throws UserMissingParameterException, EmailNotValidException, PasswordNotValidException, LocationMissingParameterException {

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

    public User updateUser(UserForm userForm, Long id) throws UserNotFoundException, UserMissingParameterException, LocationMissingParameterException, EmailNotValidException, PasswordNotValidException {

        Optional<User> user = userRepository.findById(id);
        User actualUser = user.get();

        if (actualUser == null)
            throw new UserNotFoundException("The user you searched for is not found!");

        checkUserForm(userForm);

        actualUser.getLocation().setLongitude(userForm.getLocation().getLongitude());
        actualUser.getLocation().setLatitude(userForm.getLocation().getLatitude());
        actualUser.getLocation().setCountry(userForm.getLocation().getCountry());
        actualUser.getLocation().setCity(userForm.getLocation().getCity());

        locationRepository.save(actualUser.getLocation());

        if (userForm.getRoles() != null) {
            for (Role r : userForm.getRoles()) {
                actualUser.getRoles().add(r);
                roleRepository.save(r);
            }
        }

        return userRepository.save(actualUser);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> findByNickname(String nickname) {
        List<User> all = userRepository.findAllByNickname(nickname);
        //all.stream().filter(user -> user.getNickname().equals(nickname)).collect(Collectors.toList());
        return all;
    }

    public List<User> findByLocationCity(String city) {
        List<User> all = userRepository.findAllByLocationCityContaining(city);
      //  all.stream().filter(user -> user.getLocation().getCity().equals(city)).collect(Collectors.toList());
        return all;
    }

    public List<User> findByUsername(String username) {
        List<User> all = userRepository.findAllByUsername(username);
        return all;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("User not found");
        System.out.println(user.getFirstName());
        return new UserDetailsImpl(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) throws UserMissingParameterException {
        Optional<User> user = userRepository.findById(id);
        if (user == null)
            throw new UserMissingParameterException("id not found");
        return new UserDetailsImpl(user.get());
    }
}





