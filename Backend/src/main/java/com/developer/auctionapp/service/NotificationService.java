package com.developer.auctionapp.service;

import com.developer.auctionapp.dto.request.NotificationRequest;
import org.springframework.http.ResponseEntity;

public interface NotificationService {

    ResponseEntity<Object> sendNotificationWhenUserIsOutbided(NotificationRequest notificationRequest);

    ResponseEntity<Object> sendNotificationWhenAuctionIsFinished(String message);

    ResponseEntity<Object> getNotificationsByUserId(long id);
}

