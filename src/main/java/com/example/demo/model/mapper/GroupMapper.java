package com.example.demo.model.mapper;

import com.example.demo.entity.PaymentGroup;
import com.example.demo.entity.UserGroup;
import com.example.demo.model.dto.PaymentGroupBody;

import java.util.HashSet;
import java.util.Set;

public class GroupMapper {
    public static PaymentGroupBody toPaymentGroupBody(PaymentGroup paymentGroup) {

        Set<Integer> userIds = new HashSet<>();
        for (UserGroup userGroup : paymentGroup.getUsers()) {
            userIds.add(userGroup.getUser().getId());
        }
        return new PaymentGroupBody(
                paymentGroup.getId(),
                paymentGroup.getHouse().getId(),
                paymentGroup.getName(),
                userIds
        );
    }
}
