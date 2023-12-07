package com.example.demo.service;

import com.example.demo.entity.House;
import com.example.demo.entity.UserHouse;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.model.mapper.UserMapper;
import com.example.demo.repository.HouseRepository;
import com.example.demo.repository.UserHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class HouseServiceImp implements HouseService{
    private final HouseRepository houseRepository;
    private final UserHouseRepository userHouseRepository;
    @Autowired
    public HouseServiceImp(HouseRepository houseRepository, UserHouseRepository userHouseRepository) {
        this.houseRepository = houseRepository;
        this.userHouseRepository = userHouseRepository;
    }

    @Override
    public List<House> getAllHouse() {
        return houseRepository.findAll();
    }

    @Override
    public House getHouseById(String id) {
        return houseRepository.getHouseById(id);
    }

    @Override
    public Set<UserDTO> getUsersHouseById(String id) {
        Set<UserDTO> users = new HashSet<>();
         for(UserHouse userHouse: houseRepository.getHouseById(id).getUserHouses()){
             users.add(UserMapper.toUserDto(userHouse.getUser()));
         }
        return users;
    }

    @Override
    public Boolean existsById(String id) {
        return houseRepository.existsById(id);
    }

    @Override
    public Boolean existsUserHouseByHouse_IdAndUser_Email(String email, String id) {
        return userHouseRepository.existsUserHouseByHouse_IdAndUser_Email(id,email);
    }

    @Override
    public void saveHouse(House house) {
        houseRepository.save(house);
    }

    @Override
    public void joinHouse(UserHouse userHouse) {
        userHouseRepository.save(userHouse);
    }
}
