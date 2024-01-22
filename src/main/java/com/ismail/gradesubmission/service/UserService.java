package com.ismail.gradesubmission.service;

import com.ismail.gradesubmission.entity.User;

public interface UserService {
    User getUser(Long id);

    User getUser(String username);

    User saveUser(User user);

}