package org.example.bankingsystem.services;

import org.example.bankingsystem.entities.User;
import org.example.bankingsystem.exceptions.InvalidPasswordException;
import org.example.bankingsystem.exceptions.UserNotFoundException;
import org.example.bankingsystem.repository.UserRepository;
import org.example.bankingsystem.requests.LoginRequest;
import org.example.bankingsystem.requests.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    //method to add user
    public User addUser(UserRequest request){
        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        return userRepository.save(user);
    }

    //method to get user login
    public User loginUser(LoginRequest request){
        User user = userRepository.findByEmail(request.getEmail());
        if(user == null){
            throw new UserNotFoundException("user not found!");
        }
        if (!user.getPassword().equals(request.getPassword())) {
            throw new InvalidPasswordException("Invalid Password!");
        }
        return user;
    }
//    public User getUser(int id){
//        User u = null;
//        u = userRepository.findById(id);
//        return u;
//    }

    //method to update user

    public User updateUser(UserRequest request, Integer id){
        Optional<User> optional = userRepository.findById(id);
        if(optional.isPresent()){
            User oldUser = optional.get();
            oldUser.setFullName(request.getFullName());
            oldUser.setEmail(request.getEmail());
            oldUser.setPassword(request.getPassword());
            return userRepository.save(oldUser);
        }

        return null;
    }

    //method to delete user
    public void deleteUser(Integer id){
        userRepository.deleteById(id);
    }
}
