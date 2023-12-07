package com.example.demo.service;

import com.example.demo.entity.PaymentGroup;
import com.example.demo.entity.User;
import com.example.demo.model.dto.HouseWithRole;
import com.example.demo.model.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface UserService {
    public User getUserById(Integer id);
    public List<UserDTO> getAllUser();
    public UserDTO getUserDTOByEmail(String email);
    public Set<PaymentGroup> getUserGroups(Integer userId);
    public User getUserByEmail(String email);
    public void saveUser(User user);
    public boolean isEmailExist(String email);
    public Set<HouseWithRole> getHouseByEmail(String email);
    public boolean existUserHouseByUserId(int id);
}
