package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Component
@Table(name = "record")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private long money;
    private String date;
    private String information;
    private int paymentGroup;
    private boolean paid;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "payer_id")
    private User payer;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "house_id")
    private House house;
    @ManyToMany(mappedBy = "records",cascade = {CascadeType.MERGE})
    @JsonIgnore
    private Set<User> participants;
}