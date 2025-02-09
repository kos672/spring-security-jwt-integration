package com.kkuzmin.demo.user;

import com.kkuzmin.demo.exception.UserAlreadyExistsException;
import com.kkuzmin.demo.exception.UserNotExistsException;
import com.kkuzmin.demo.exception.WrongPasswordException;
import com.kkuzmin.demo.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    private final Object mutex = new Object();

    @Autowired
    public UserService(UserRepository userRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public String generateTokenForUser(String username, String password) {
        Optional<User> user = userRepository.findById(username);
        if (user.isEmpty()) {
            throw new UserNotExistsException(username);
        }

        if (passwordEncoder.matches(password, user.get().getPassword())) {
            return jwtUtil.generateToken(username);
        } else {
            throw new WrongPasswordException();
        }
    }


    public void register(String username, String password) {
        synchronized (mutex) {
            if (userRepository.findById(username).isPresent()) {
                throw new UserAlreadyExistsException(username);
            }

            String encodedPassword = passwordEncoder.encode(password);
            User user = new User(username, encodedPassword, "ROLE_USER");
            userRepository.save(user);
        }
    }


}
