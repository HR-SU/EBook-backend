package com.shr.backend.service;

import com.shr.backend.entity.Authority;

public interface AuthorityService {
    Authority findOne(Integer Id);
    Authority getAuthorityByUsername(String username);
}
