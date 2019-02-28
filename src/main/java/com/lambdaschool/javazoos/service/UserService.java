package com.lambdaschool.javazoos.service;

import com.lambdaschool.javazoos.model.User;

import java.util.List;

public interface UserService
{
    User save(User user);
    List<User> findAll();
    void delete(long id);
}