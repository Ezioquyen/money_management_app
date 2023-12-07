package com.example.demo.controller;

import com.example.demo.service.PaymentGroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/groups")
public class PaymentGroupController {
    private final PaymentGroupService paymentGroupService;

    public PaymentGroupController(PaymentGroupService paymentGroupService) {
        this.paymentGroupService = paymentGroupService;
    }
    @GetMapping("")
    public ResponseEntity<?> getAllGroup(){
        return ResponseEntity.ok(paymentGroupService.getAllPaymentGroup());
    }
}
