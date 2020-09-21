package com.shr.backend.dao;

import com.shr.backend.entity.Authority;

public interface AuthorityDao {
    Authority findOne(Integer Id);
    Authority findByName(String name);
    Boolean update(Authority authority);
}
