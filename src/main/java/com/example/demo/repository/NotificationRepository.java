package com.example.demo.repository;

import com.example.demo.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Integer> {
    @Query(value = "select * from notification join user_notification on notification.id = notification_id where userid = :userId order by time desc",nativeQuery = true)
    List<Notification> getByUserId(@Param("userId") Integer userId);
    @Query(value = "select count(*) from notification where is_read = false",nativeQuery = true)
    Integer getUnreadNotificationByUserId(Integer userId);
}
