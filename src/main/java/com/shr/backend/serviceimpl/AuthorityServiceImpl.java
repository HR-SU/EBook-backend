package com.shr.backend.serviceimpl;

import com.shr.backend.dao.AuthorityDao;
import com.shr.backend.dao.UserDao;
import com.shr.backend.entity.Authority;
import com.shr.backend.entity.User;
import com.shr.backend.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    AuthorityDao authorityDao;

    @Autowired
    UserDao userDao;

    @Override
    public Authority findOne(Integer Id) { return authorityDao.findOne(Id); }

    @Override
    public Authority getAuthorityByUsername(String username) {
        User user = userDao.findByName(username);
        if(user == null) return null;
        return user.getAuthority();
    }
}
