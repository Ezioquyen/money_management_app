package com.example.demo.controller;

import com.example.demo.entity.UserGroup;
import com.example.demo.model.dto.PaymentGroupBody;
import com.example.demo.service.PaymentGroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/groups")
public class PaymentGroupController {
    private final PaymentGroupService paymentGroupService;

    public PaymentGroupController(PaymentGroupService paymentGroupService) {
        this.paymentGroupService = paymentGroupService;
    }
    @PostMapping("/create")
    public String createGroup(@RequestBody PaymentGroupBody paymentGroupBody){
        paymentGroupService.createGroup(paymentGroupBody);
        return "done";
    }
    @PostMapping("/add")
    public String addMember(@RequestBody UserGroup userGroup){
        paymentGroupService.addUserToGroup(userGroup);
        return  "done";
    }
    @GetMapping("/{houseId}/{userId}")
    public ResponseEntity<?> getGroupByUserAndHouse(@PathVariable String houseId, @PathVariable Integer userId){
        return ResponseEntity.ok(paymentGroupService.getPaymentGroupByUserIdAndHouseId(userId, houseId));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getGroupById(@PathVariable Integer id){
        return ResponseEntity.ok(paymentGroupService.getPaymentGroupById(id));
    }
    @GetMapping("/house/{houseId}")
    public ResponseEntity<?> getGroupByHouse(@PathVariable String houseId){
        return ResponseEntity.ok(paymentGroupService.getPaymentGroupByHouseId(houseId));
    }


}
