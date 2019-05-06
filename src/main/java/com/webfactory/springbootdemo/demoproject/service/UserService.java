package com.webfactory.springbootdemo.demoproject.service;

import com.webfactory.springbootdemo.demoproject.exeptions.UserMissingParametarException;
import com.webfactory.springbootdemo.demoproject.exeptions.UserNotFoundException;
import com.webfactory.springbootdemo.demoproject.model.User;
import com.webfactory.springbootdemo.demoproject.model.UserForm;
import com.webfactory.springbootdemo.demoproject.persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User createUser(UserForm userForm) throws  UserMissingParametarException{

        User userNew = new User();
        if(userForm.getEmail().equals(""))
            throw new UserMissingParametarException("Missing parameter -> Email");
        else if(userForm.getFirstName().equals(""))
            throw  new UserMissingParametarException("Missing parameter -> First Name");
        else if(userForm.getLastName().equals(""))
            throw new UserMissingParametarException("Missing parameter -> Last Name");
        else if(userForm.getNickname().equals(""))
            throw new UserMissingParametarException("Missing parameter -> Nickname");
        else if(userForm.getPassword().equals(""))
            throw new UserMissingParametarException("Missing parameter -> Password");
        else
        {
            userNew.setEmail(userForm.getEmail());
            userNew.setFirstName(userForm.getFirstName());
            userNew.setLastName(userForm.getLastName());
            userNew.setPassword(userForm.getPassword());
            userNew.setNickname(userForm.getNickname());
        }

            return userRepository.save(userNew);
    }

    public User updateUser(UserForm userForm,Long id) throws UserNotFoundException, UserMissingParametarException {

        Optional<User> user = userRepository.findById(id);
        User actualUser = user.get();

        if(actualUser == null)
            throw new UserNotFoundException("The user you searched for is not found!");

        if(userForm.getPassword() != null)
            actualUser.setPassword(userForm.getPassword());
        if(userForm.getNickname() != null)
            actualUser.setNickname(userForm.getNickname());
        if(userForm.getLastName() != null)
            actualUser.setLastName(userForm.getLastName());
        if(userForm.getFirstName() != null)
            actualUser.setFirstName(userForm.getFirstName());
        if(userForm.getEmail() != null)
            actualUser.setEmail(userForm.getEmail());

        System.out.println(user);
        System.out.println(actualUser);
        return userRepository.save(actualUser);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}


