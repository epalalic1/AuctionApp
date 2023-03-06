package com.developer.auctionapp.controller;

import com.developer.auctionapp.dto.request.NotificationRequest;
import com.developer.auctionapp.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

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
    public void sendToSpecificUser(@Payload NotificationRequest notificationRequest,  Principal principal) {
        System.out.println("This route is also good");
       // String email = "epalalic1@etf.unsa.ba";
       // simpMessagingTemplate.convertAndSend("/specific/epalalic1@etf.unsa.ba",string);
        //simpMessagingTemplate.send("/",);
        //simpMessagingTemplate.convertAndSend("/specific", notificationRequest.getMessage());
        notificationService.sendNotificationWhenUserIsOutbided(notificationRequest);
    }
}
