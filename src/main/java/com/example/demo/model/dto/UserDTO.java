package com.example.demo.model.dto;

import com.example.demo.entity.*;
import com.example.demo.entity.Record;
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
    private Set<UserGroup> paymentGroups;
    @JsonIgnore
    private Set<UserHouse> userHouses;
    @JsonIgnore
    private Set<UserRecord> Records;
}