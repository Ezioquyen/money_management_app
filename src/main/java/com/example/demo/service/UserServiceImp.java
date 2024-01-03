package com.example.demo.service;

import com.example.demo.entity.PaymentGroup;
import com.example.demo.entity.User;
import com.example.demo.entity.UserHouse;
import com.example.demo.model.dto.HouseWithRole;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.model.mapper.HouseMapper;
import com.example.demo.model.mapper.UserMapper;
import com.example.demo.repository.UserHouseRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component

public class UserServiceImp implements UserService{

    private final UserRepository userRepository;
    private final UserHouseRepository userHouseRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository, UserHouseRepository userHouseRepository) {
        this.userRepository = userRepository;
        this.userHouseRepository = userHouseRepository;
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.getUserById(id);
    }

    @Override
    public UserDTO getUserDTOById(Integer id) {
        return UserMapper.toUserDto(userRepository.getUserById(id));
    }

    @Override
    public List<UserDTO> getAllUser(){
        List<UserDTO> userDTOList = new ArrayList<>();
        for(User user : userRepository.findAll()){
            userDTOList.add(UserMapper.toUserDto(user));
        }
        return userDTOList;
    }


    @Override
    public Set<PaymentGroup> getUserGroups(Integer userId) {
        UserDTO user = UserMapper.toUserDto(userRepository.getUserById(userId));
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public boolean isEmailExist(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Set<HouseWithRole> getHouseByEmail(String email) {
        Set<HouseWithRole> houseWithRoles = new HashSet<>();
        for(UserHouse userHouse : userHouseRepository.findUserHouseByUser_Email(email)){
            houseWithRoles.add(HouseMapper.toHouseWRole(userHouse));
        }
        return houseWithRoles;
    }


    @Override
    public void updateUserToken(Integer id,String deviceToken) {
        userRepository.updateDeviceTokenById(id,deviceToken);
    }

    @Override
    public boolean existUserHouseByUserId(Integer id) {
        return userHouseRepository.existsUserHouseByUser_Id(id);
    }

    @Override
    public UserDTO getUserDTOByEmail(String email) {
        return UserMapper.toUserDto(userRepository.getUserByEmail(email));
    }
}
