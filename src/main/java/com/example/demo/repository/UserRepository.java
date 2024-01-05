package com.example.demo.repository;

import com.example.demo.entity.User;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    public User getUserById(Integer userId);
    public User getUserByEmail(String email);
    public Boolean existsByEmail(String email);
    @Modifying
    @Transactional
    @Query(value = "update user  set device_token = :deviceToken where id = :userId",nativeQuery = true)
    public void updateDeviceTokenById(@Param("userId") Integer userId,@Param("deviceToken") String deviceToken);
}
