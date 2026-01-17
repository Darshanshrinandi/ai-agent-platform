package com.darshan.ai.agentplatform.Controller;

import com.darshan.ai.agentplatform.DTO.LoginRequest;
import com.darshan.ai.agentplatform.DTO.RegisterUserRequest;
import com.darshan.ai.agentplatform.Entity.User;
import com.darshan.ai.agentplatform.security.JwtUtil;
import com.darshan.ai.agentplatform.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterUserRequest request) {
        User user = new User();

        user.setName(request.getName());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());

        userService.registerUser(user);

        return ResponseEntity.ok("Register Success");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        String token = jwtUtil.generateToken(authentication.getName());

        return ResponseEntity.ok(Map.of("token", token));
    }

}
