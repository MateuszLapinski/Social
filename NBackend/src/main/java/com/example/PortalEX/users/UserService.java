package com.example.PortalEX.users;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ObjectMapper objectMapper;


    @GetMapping("/users")
    public ResponseEntity getUsers() throws JsonProcessingException {
            List<User> users= userRepository.findAll();
            return ResponseEntity.ok(objectMapper.writeValueAsString(users));
    }


    @PostMapping("/users")
    public ResponseEntity addUser(@RequestBody User user){
        Optional<User> userFromDB = userRepository.findByUsername(user.getUsername());

        if(userFromDB.isPresent()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user) {
        Optional<User> userFromDB = userRepository.findByUsername(user.getUsername());

        if(userFromDB.isEmpty()||wrongPassword(userFromDB,user)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok().build();
    }

    private boolean wrongPassword(Optional<User> userFromDB, User user) {
        return !userFromDB.get().getPassword().equals(user.getPassword());
    }

}
