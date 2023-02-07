package com.developer.auctionapp.controller;

import com.developer.auctionapp.dto.request.NotificationRequest;
import com.developer.auctionapp.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.security.Principal;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
public class NotificationController {

    private final NotificationService notificationService;

    private SimpMessagingTemplate simpMessagingTemplate;

    public NotificationController(NotificationService notificationService, SimpMessagingTemplate simpMessagingTemplate) {
        this.notificationService = notificationService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/finishedAuction")
    @SendToUser("/user/queue")
    public ResponseEntity<Object> sendPrivateNotificationFinishedAuction (@Payload NotificationRequest notificationRequest) {
        return notificationService.sendNotificationWhenAuctionIsFinished(notificationRequest);
    }

    @MessageMapping("/outbided")
    @SendToUser("user/queue")
    public ResponseEntity<Object> sendPrivateNotificationUserOutbided (@Payload  NotificationRequest notificationRequest) {
        return notificationService.sendNotificationWhenUserIsOutbided(notificationRequest);
    }

    /*@MessageMapping("/message")
    public ResponseEntity<Object> sendingMessage(@Payload String string) {
        System.out.println("The route is good");
        return notificationService.sendingMessage(string);
    }

    @MessageMapping("/application")
    @SendTo("/all/messages")
    public String send(final String string) throws Exception {
        System.out.println("Route is good");
        return string;
    }*/



    @MessageMapping("/private")
    public void sendToSpecificUser(@Payload String string,  Principal principal) {
        System.out.println("This route is also good");
        String email = "epalalic1@etf.unsa.ba";
        simpMessagingTemplate.convertAndSendToUser(email, "/specific", string);
    }
}
