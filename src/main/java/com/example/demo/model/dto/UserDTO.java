package com.example.demo.model.dto;

import com.example.demo.entity.PaymentGroup;
import com.example.demo.entity.Record;
import com.example.demo.entity.UserHouse;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    int id;
    private String username;
    private String email;
    @JsonIgnore
    private Set<PaymentGroup> paymentGroups;
    @JsonIgnore
    private Set<UserHouse> userHouses;
    @JsonIgnore
    private Set<Record> Records;
}