package com.developer.auctionapp.repository;

import com.developer.auctionapp.entity.Notification;
import com.developer.auctionapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {
    @Query
    List<Notification> findByUser(User user);
}
