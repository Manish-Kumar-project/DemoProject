package com.example.demo.service.interf;

import com.example.demo.Entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<User> getAllUsers();
    User getUser(Long id);
    User updateUser(User user);

}
