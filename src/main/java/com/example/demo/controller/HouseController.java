package com.example.demo.controller;

import com.example.demo.entity.House;
import com.example.demo.entity.UserHouse;
import com.example.demo.service.HouseService;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/houses")
public class HouseController {
    private final HouseService houseService;
    private final UserService userService;
    public HouseController(HouseService houseService, UserService userService) {
        this.houseService = houseService;
        this.userService = userService;
    }
    @GetMapping("")
    public ResponseEntity<?> getHouses(){
        List<House> houses = houseService.getAllHouse();
        return ResponseEntity.ok(houses);
    }
    @GetMapping("/{id}/users")
    public ResponseEntity<?> getUsersById(@PathVariable String id){
        return ResponseEntity.ok(houseService.getUsersHouseById(id));
    }
    @GetMapping("/{id}/check")
    public ResponseEntity<?> isHouseExist(@PathVariable String id){
        return ResponseEntity.ok(houseService.existsById(id));
    }
    @GetMapping("/{id}/{email}")
    public ResponseEntity<?> getUsersById(@PathVariable String email,@PathVariable String id){
        return ResponseEntity.ok(houseService.existsUserHouseByHouse_IdAndUser_Email(email, id));
    }
    @PostMapping("/join/{houseId}/{userId}")
    public void joinHouse(@RequestBody UserHouse userHouse,@PathVariable String houseId, @PathVariable Integer userId){
        userHouse.setHouse(houseService.getHouseById(houseId));
        userHouse.setUser(userService.getUserById(userId));
        houseService.joinHouse(userHouse);
    }
    @PostMapping("/create")
    public void createHouse(@RequestBody House House){
        houseService.saveHouse(House);
    }
}
