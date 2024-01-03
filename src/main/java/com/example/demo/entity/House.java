package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Component
@Table(name = "house")
public class House {
    @Id
    private String id;
    private String name;
    private String information;
    private Date date;
    @OneToMany(mappedBy = "house")
    @JsonIgnore
    private Set<UserHouse> userHouses;
}
