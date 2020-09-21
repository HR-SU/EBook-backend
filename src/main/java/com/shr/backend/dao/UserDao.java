package com.shr.backend.dao;

import com.shr.backend.entity.User;

import java.util.List;

public interface UserDao {
    User findOne(Integer Id);
    Boolean update(User user);
    User findByName(String name);
    List<User> findAll();
}
