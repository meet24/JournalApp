package com.example.api.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.api.entity.UserEntry;
import com.example.api.repository.UserRepository;

@Component
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public void saveEntry(UserEntry user) {
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

    public UserEntry findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}