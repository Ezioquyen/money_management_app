package com.example.demo.service;

import com.example.demo.entity.PaymentGroup;
import com.example.demo.repository.PaymentGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentGroupServiceImp implements PaymentGroupService{
    private final PaymentGroupRepository paymentGroupRepository;
    @Autowired
    public PaymentGroupServiceImp(PaymentGroupRepository paymentGroupRepository) {
        this.paymentGroupRepository = paymentGroupRepository;
    }

    @Override
    public List<PaymentGroup> getAllPaymentGroup() {
        return paymentGroupRepository.findAll();
    }
}
