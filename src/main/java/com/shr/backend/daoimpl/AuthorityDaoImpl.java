package com.shr.backend.daoimpl;

import com.shr.backend.dao.AuthorityDao;
import com.shr.backend.entity.Authority;
import com.shr.backend.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorityDaoImpl implements AuthorityDao {
    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public Authority findOne(Integer Id) { return authorityRepository.getOne(Id); }

    @Override
    public Authority findByName(String name) { return authorityRepository.findByName(name); }

    @Override
    public Boolean update(Authority authority) {
        authorityRepository.save(authority);
        return true;
    }
}
