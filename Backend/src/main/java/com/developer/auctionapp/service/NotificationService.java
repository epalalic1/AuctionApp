package com.developer.auctionapp.service;

import com.developer.auctionapp.dto.request.NotificationRequest;
import org.springframework.http.ResponseEntity;

public interface NotificationService {

    ResponseEntity<Object> sendNotificationWhenUserIsOutbided(NotificationRequest notificationRequest);

    ResponseEntity<Object> sendNotificationWhenAuctionIsFinished(NotificationRequest notificationRequest);

    ResponseEntity<Object> sendingMessage(String string);
}
