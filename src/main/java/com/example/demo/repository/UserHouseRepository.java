package com.example.demo.repository;

import com.example.demo.entity.UserHouse;
import com.example.demo.serializable.UserHouseId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface UserHouseRepository extends JpaRepository<UserHouse, UserHouseId> {
    public Set<UserHouse> findUserHouseByUser_Email(String email);
    public Boolean existsUserHouseByHouse_IdAndUser_Email(String id, String email);
    public Boolean existsUserHouseByUser_Id(Integer id);
}
