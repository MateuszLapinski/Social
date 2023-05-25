package com.example.PortalEX.posts;

import com.example.PortalEX.users.User;
import com.example.PortalEX.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class PostService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;


    @PostMapping("/posts")
    public ResponseEntity addPosts(@RequestHeader("username") String username, @RequestBody String postbody){
        Optional<User> userFromDb = userRepository.findByUsername(username);

        if(userFromDb.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Post post = new Post(userFromDb.get(), postbody);

        Object savedPost = postRepository.save(post);

        return ResponseEntity.ok(savedPost);
    }

}
