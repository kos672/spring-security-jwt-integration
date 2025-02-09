package com.kkuzmin.demo.auth;

import com.kkuzmin.demo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestParam(name="username") String username, @RequestParam(name="password") String password) {
        userService.register(username, password);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/auth")
    public ResponseEntity<String> authenticate(@RequestParam(name="username") String username, @RequestParam(name="password") String password) {
        String token = userService.generateTokenForUser(username, password);
        return ResponseEntity.ok(token);
    }

}

