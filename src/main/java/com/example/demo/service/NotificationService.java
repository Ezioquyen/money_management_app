package com.example.demo.service;

import com.example.demo.entity.Notification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface NotificationService {
    List<Notification> getNotificationByUserId(Integer id);
    void createNotification(Map<String,Object> notification);
    Integer getUnReadNotificationByUserId(Integer integer);

}
