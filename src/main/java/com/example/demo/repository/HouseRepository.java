package com.example.demo.repository;

import com.example.demo.entity.House;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends JpaRepository<House,String> {
    public House getHouseById(String id);
    public boolean existsById(String id);
}
