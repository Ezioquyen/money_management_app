package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecordBody {
    private Integer id;

    private long money;
    private Date date;
    private String information;
    private int paymentGroup;
    private boolean paid;
    private int payerId;
    private String houseId;
    private Set<Integer> participantIds;
}
