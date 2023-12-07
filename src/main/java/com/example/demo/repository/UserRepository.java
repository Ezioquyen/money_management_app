package com.example.demo.repository;

import com.example.demo.entity.PaymentGroup;
import com.example.demo.entity.User;
import com.example.demo.entity.UserHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    public User getUserById(Integer userId);
    public User getUserByEmail(String email);
    public Boolean existsByEmail(String email);
}
