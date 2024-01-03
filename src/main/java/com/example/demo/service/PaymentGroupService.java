package com.example.demo.service;

import com.example.demo.entity.PaymentGroup;
import com.example.demo.entity.UserGroup;
import com.example.demo.model.dto.PaymentGroupBody;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentGroupService {
    public PaymentGroupBody getPaymentGroupById(Integer id);
    public List<PaymentGroupBody> getPaymentGroupByUserIdAndHouseId(Integer userId, String houseId);
    public void createGroup(PaymentGroupBody groupBody);
    public void addUserToGroup(UserGroup userGroup);
    public List<PaymentGroupBody> getPaymentGroupByHouseId(String houseId);
}
