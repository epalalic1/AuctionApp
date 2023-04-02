package com.developer.auctionapp.controller;

import com.developer.auctionapp.dto.request.NotificationRequest;
import com.developer.auctionapp.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @MessageMapping("/private")
    public void sendToSpecificUser(@Payload NotificationRequest notificationRequest, Principal principal) {
        notificationService.sendNotificationWhenUserIsOutbided(notificationRequest);
    }

    @GetMapping("/auctionapp/getNotificationsForUser")
    @ResponseBody
    public ResponseEntity<Object> getNotificationsByUserIdMethod(@RequestParam(name = "id") long id) {
        return notificationService.getNotificationsByUserId(id);
    }

    @MessageMapping("/private/finishedAuction")
    public ResponseEntity<Object> sendNotificationToUsersWhenAuctionIsFinished(@Payload String message) {
        return notificationService.sendNotificationWhenAuctionIsFinished(message);
    }
}

