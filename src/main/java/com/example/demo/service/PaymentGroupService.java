package com.example.demo.service;

import com.example.demo.entity.PaymentGroup;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentGroupService {
    public List<PaymentGroup> getAllPaymentGroup();
}
