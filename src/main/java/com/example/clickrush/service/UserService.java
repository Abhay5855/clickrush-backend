package com.example.clickrush.service;

import com.example.clickrush.models.User;
import com.example.clickrush.repositories.UserRepository;
import com.example.clickrush.serviceImp.UserServiceImp;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserServiceImp {

    private final UserRepository userRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
}
