package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.type.DateTime;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name="notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    private String deepLink;
    private String title;
    private String notificationText;
    private String image;
    private String name;
    private Boolean isRead;
    private LocalDateTime time;
    @OneToMany(mappedBy = "notification")
    @JsonIgnore
    private List<UserNotification>  users;
}
