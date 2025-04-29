package com.example.api.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.example.api.entity.UserEntry;
import com.example.api.repository.UserRepository;

@Component
public class UserService {    
    
    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 

    public boolean saveNewUser(UserEntry user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void saveUser(UserEntry user) {
        userRepository.save(user);
    }

    public void saveAdmin(UserEntry user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER", "ADMIN"));
        userRepository.save(user);
    }

    public List<UserEntry> getAll() {
        return userRepository.findAll();
    }

    public Optional<UserEntry> getByID(ObjectId Id){
        return userRepository.findById(Id);
    }

    public void deleteByID(ObjectId Id) {
        userRepository.deleteById(Id);
    }

    // public void deleteByUserName(String userName) {
    //     userRepository.deleteByUserName(userName);
    // }

    public UserEntry findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}