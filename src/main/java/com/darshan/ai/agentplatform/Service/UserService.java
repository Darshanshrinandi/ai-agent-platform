package com.darshan.ai.agentplatform.Service;

import com.darshan.ai.agentplatform.Entity.User;
import com.darshan.ai.agentplatform.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("User already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


    public User login(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!user.getPassword().equals(password)) {
            log.error("Invalid email or password");
        }

        return user;
    }

    public User getByEmail(String email) {

        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        return user;

    }

    public User getById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return user;
    }
}
