package com.developer.auctionapp.controller;

import com.developer.auctionapp.dto.request.NotificationRequest;
import com.developer.auctionapp.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequestMapping("/auctionapp/notification")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @MessageMapping("/finishedAuction")
    public ResponseEntity<Object> sendPrivateNotificationFinishedAuction (@Payload NotificationRequest notificationRequest) {
        return notificationService.sendNotificationWhenAuctionIsFinished(notificationRequest);
    }

    @MessageMapping("/outbided")
    @SendToUser
    public ResponseEntity<Object> sendPrivateNotificationUserOutbided (@Payload  NotificationRequest notificationRequest) {
        return notificationService.sendNotificationWhenUserIsOutbided(notificationRequest);
    }
}
