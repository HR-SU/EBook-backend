package com.shr.backend.repository;

import com.shr.backend.entity.Shopcart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopcartRepository extends JpaRepository<Shopcart, Integer> {
}
