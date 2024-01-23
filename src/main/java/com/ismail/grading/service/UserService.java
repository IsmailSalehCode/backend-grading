package com.ismail.grading.service;

import com.ismail.grading.entity.User;

public interface UserService {
    User getUser(Long id);

    User getUser(String username);

    User saveUser(User user);

}