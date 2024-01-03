package com.example.demo.controller;

import com.example.demo.model.dto.UserDTO;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<?> getListUser(){
        List<UserDTO> users = userService.getAllUser();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getUserByEmail(@PathVariable Integer id){

        return ResponseEntity.ok(userService.getUserDTOById(id));
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email){
        return ResponseEntity.ok(userService.getUserDTOByEmail(email));
    }
    @GetMapping("/houses/{email}")
    public ResponseEntity<?> getListHouseOfUser(@PathVariable String email){
        return ResponseEntity.ok(userService.getHouseByEmail(email));
    }

    @GetMapping("/groups/{id}")
    public ResponseEntity<?> getUserGroup(@PathVariable int id){
        return ResponseEntity.ok(userService.getUserGroups(id));
    }
    @GetMapping("/house_check/{id}")
    public ResponseEntity<?> isUserHasHouses(@PathVariable int id){
        return ResponseEntity.ok(userService.existUserHouseByUserId(id));
    }
}
