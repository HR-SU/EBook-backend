package com.shr.backend.dao;

import com.shr.backend.entity.Shopcart;

public interface ShopcartDao {
    Shopcart findOne(Integer Id);
    Boolean update(Shopcart shopcart);
    Boolean deleteById(Integer Id);
}
