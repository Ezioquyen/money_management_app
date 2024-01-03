package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentGroupBody {
    private Integer id;
    private String houseId;
    private String name;
    Set<Integer> userIds;
}
