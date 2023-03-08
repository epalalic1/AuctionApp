package com.developer.auctionapp.service;

import com.developer.auctionapp.dto.request.NotificationRequest;
import com.developer.auctionapp.dto.response.ProductResponse;
import org.springframework.http.ResponseEntity;

public interface NotificationService {

    ResponseEntity<Object> sendNotificationWhenUserIsOutbided(NotificationRequest notificationRequest);

    ResponseEntity<Object> sendNotificationWhenAuctionIsFinished(NotificationRequest notificationRequest);

    ResponseEntity<Object> getNotificationsByUserId(long id);
}
