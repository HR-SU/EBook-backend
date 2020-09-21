package com.shr.backend.daoimpl;

import com.shr.backend.dao.ShopcartDao;
import com.shr.backend.entity.Shopcart;
import com.shr.backend.repository.ShopcartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ShopcartDaoImpl implements ShopcartDao {
    @Autowired
    private ShopcartRepository shopcartRepository;

    @Override
    public Shopcart findOne(Integer Id) { return shopcartRepository.getOne(Id); }

    @Override
    public Boolean update(Shopcart shopcart) {
        shopcartRepository.save(shopcart);
        return true;
    }

    @Override
    public Boolean deleteById(Integer Id) {
        if(!shopcartRepository.existsById(Id)) return false;
        shopcartRepository.deleteById(Id);
        return true;
    }
}
