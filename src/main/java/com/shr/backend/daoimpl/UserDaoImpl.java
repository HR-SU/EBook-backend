package com.shr.backend.daoimpl;

import com.shr.backend.dao.UserDao;
import com.shr.backend.entity.User;
import com.shr.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findOne(Integer Id) { return userRepository.getOne(Id); }

    @Override
    public Boolean update(User user) {
        userRepository.save(user);
        return true;
    }

    @Override
    public User findByName(String name) { return userRepository.findByUserName(name); }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
