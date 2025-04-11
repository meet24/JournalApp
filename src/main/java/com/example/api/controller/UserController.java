package com.example.api.controller;

import org.springframework.web.bind.annotation.RestController;
import com.example.api.entity.UserEntry;
import com.example.api.repository.UserRepository;
import com.example.api.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    // @GetMapping("/")
    // public List<UserEntry> getAllUsers() {
    //     return userService.getAll();
    // }

    // @PostMapping("/")
    // public void createUser(@RequestBody UserEntry user) {
    //     userService.saveEntry(user);
    // }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UserEntry userEntry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        UserEntry userInDb = userService.findByUserName(userName);

        if(userInDb != null) {
            userInDb.setUserName(userEntry.getUserName());
            userInDb.setPassword(userEntry.getPassword());
            userService.saveEntry(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}