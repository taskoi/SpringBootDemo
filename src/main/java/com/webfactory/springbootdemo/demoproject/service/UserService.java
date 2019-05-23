package com.webfactory.springbootdemo.demoproject.service;

import com.webfactory.springbootdemo.demoproject.exeptions.LocationMissingParameterException;
import com.webfactory.springbootdemo.demoproject.exeptions.UserMissingParametarException;
import com.webfactory.springbootdemo.demoproject.exeptions.UserNotFoundException;
import com.webfactory.springbootdemo.demoproject.model.*;
import com.webfactory.springbootdemo.demoproject.persistance.LocationRepository;
import com.webfactory.springbootdemo.demoproject.persistance.RoleRepository;
import com.webfactory.springbootdemo.demoproject.persistance.UserRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
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


//    @PostConstruct
//    private void addUsers() {
//        User user = new User();
//        user.setUsername("ivan");
//        user.setPassword("Password0102!@");
//        user.setFirstName("Ivan");
//        user.setLastName("Tasevski");
//        user.setEmail("ivan.tasevski@webfactory.mk");
//        user.setNickname("tasko");
//        Location location = new Location();
//        location.setCity("skopje");
//        location.setLongitude((float)22.22);
//        location.setLatitude((float)33.33);
//        location.setCountry("Mk");
//        user.setLocation(location);
//        user.getRoles().add("USER");
//        locationRepository.save(location);
//        userRepository.save(user);
//    }

    public User createUser(UserForm userForm) throws UserMissingParametarException {

        User user = new User();
        Location location = new Location();
        List<Role> roles = new ArrayList<>();

        if (userForm.getEmail().equals(""))
            throw new UserMissingParametarException("Missing parameter -> Email");
        else if (userForm.getFirstName().equals(""))
            throw new UserMissingParametarException("Missing parameter -> First Name");
        else if (userForm.getLastName().equals(""))
            throw new UserMissingParametarException("Missing parameter -> Last Name");
        else if (userForm.getNickname().equals(""))
            throw new UserMissingParametarException("Missing parameter -> Nickname");
        else if (userForm.getPassword().equals(""))
            throw new UserMissingParametarException("Missing parameter -> Password");
        else if (userForm.getLocation() == null) {
            throw new UserMissingParametarException("Missing parameter -> User's location");
        } else if (userForm.getRoles() == null) {
            throw new UserMissingParametarException("Missing parameter -> User's role");
        } else if (userForm.getUsername().equals(""))
            throw new UserMissingParametarException("Missing parameter -> Username");
        else {
            user.setEmail(userForm.getEmail());
            user.setFirstName(userForm.getFirstName());
            user.setLastName(userForm.getLastName());
            user.setPassword(userForm.getPassword());
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
        }

        locationRepository.save(location);
        return userRepository.save(user);
    }

    public User updateUser(UserForm userForm, Long id) throws UserNotFoundException, UserMissingParametarException, LocationMissingParameterException {

        Optional<User> user = userRepository.findById(id);
        User actualUser = user.get();

        if (actualUser == null)
            throw new UserNotFoundException("The user you searched for is not found!");

        if (userForm.getPassword() != null)
            actualUser.setPassword(userForm.getPassword());
        if (userForm.getNickname() != null)
            actualUser.setNickname(userForm.getNickname());
        if (userForm.getLastName() != null)
            actualUser.setLastName(userForm.getLastName());
        if (userForm.getFirstName() != null)
            actualUser.setFirstName(userForm.getFirstName());
        if (userForm.getEmail() != null)
            actualUser.setEmail(userForm.getEmail());
        if (userForm.getUsername() != null)
            actualUser.setUsername(userForm.getUsername());

        if (userForm.getLocation() != null) {
            if (userForm.getLocation().getCity().equals(""))
                throw new LocationMissingParameterException("Missing parameter city");
            else if (userForm.getLocation().getCountry().equals(""))
                throw new LocationMissingParameterException("Missing parameter country");
            else if (userForm.getLocation().getLatitude().equals(""))
                throw new LocationMissingParameterException("Missing parameter latitude");
            else if (userForm.getLocation().getLongitude().equals(""))
                throw new LocationMissingParameterException("Missing parameter longitude");
            else {
                actualUser.getLocation().setLongitude(userForm.getLocation().getLongitude());
                actualUser.getLocation().setLatitude(userForm.getLocation().getLatitude());
                actualUser.getLocation().setCountry(userForm.getLocation().getCountry());
                actualUser.getLocation().setCity(userForm.getLocation().getCity());

                locationRepository.save(actualUser.getLocation());
            }
        }

        if(userForm.getRoles() != null){
            for (Role r : userForm.getRoles()){
                actualUser.getRoles().add(r);
                roleRepository.save(r);
            }
        }
        System.out.println(user);
        System.out.println(actualUser);

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
        List<User> all = userRepository.findAll();
        all.stream().filter(user -> user.getNickname().equals(nickname)).collect(Collectors.toList());
        return all;
    }

    public List<User> findByLocationCity(String city) {
        List<User> all = userRepository.findAll();
        all.stream().filter(user -> user.getLocation().getCity().equals(city)).collect(Collectors.toList());
        return all;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("User not found");

        return new UserDetailsImpl(user);
    }
}





