package com.example.clickrush.serviceImp;

import com.example.clickrush.models.User;

public interface UserServiceImp {

    public User register(User user);

    String verify(User user);
}
