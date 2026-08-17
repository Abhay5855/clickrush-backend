package com.example.clickrush.service;

import com.example.clickrush.models.User;
import com.example.clickrush.repositories.UserRepository;
import com.example.clickrush.serviceImp.UserServiceImp;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserServiceImp {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    public UserService(UserRepository userRepository, AuthenticationManager authenticationManager, JWTService jwtService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    /**
     * @param user
     * @return
     */
    @Override
    public User register(User user) {
        user.setPassword_hash(encoder.encode(user.getPassword_hash()));
        return userRepository.save(user);
    }

    /**
     * @param user
     * @return
     */
    @Override
    public String verify(User user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword_hash()));

        if(authentication.isAuthenticated()){

            return jwtService.generateToken(user.getEmail());
        }
        return "fail";
    }
}
