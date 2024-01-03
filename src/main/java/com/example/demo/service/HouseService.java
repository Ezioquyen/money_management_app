package com.example.demo.service;

import com.example.demo.entity.House;

import com.example.demo.entity.UserHouse;
import com.example.demo.model.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface HouseService {
    public List<House> getAllHouse();
    public House getHouseById(String id);
    public Set<UserDTO> getUsersHouseById(String id);
    public Boolean existsById(String id);
    public Boolean existsUserHouseByHouse_IdAndUser_Email(String email, String id);
    public void saveHouse(House house);
    public void joinHouse(UserHouse userHouse);
}
