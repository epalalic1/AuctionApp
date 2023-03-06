package com.developer.auctionapp.service.impl;

import com.developer.auctionapp.dto.request.NotificationRequest;
import com.developer.auctionapp.entity.Bid;
import com.developer.auctionapp.entity.Notification;
import com.developer.auctionapp.repository.BidRepository;
import com.developer.auctionapp.repository.NotificationRepository;
import com.developer.auctionapp.repository.ProductRepository;
import com.developer.auctionapp.repository.UserRepository;
import com.developer.auctionapp.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;


/**
 *  <p>Class that implements NotificationService interface, and  we use it to send notifications to the users
 */

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    private final BidRepository bidRepository;

    private final NotificationRepository notificationRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    public NotificationServiceImpl(
            final BidRepository bidRepository,
            final NotificationRepository notificationRepository,
            final ProductRepository productRepository,
            UserRepository userRepository) {
        this.bidRepository = bidRepository;
        this.notificationRepository = notificationRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    /**
     * The method we use to send notification when users are outbided
     * @param notificationRequest DTO we receive when we need to send users notification
     * @return ResponseEntity object with the appropriate message and status
     */

    @Override
    public  ResponseEntity<Object> sendNotificationWhenUserIsOutbided(NotificationRequest notificationRequest) {
        List<Bid> list = bidRepository.findAll();
        if (list.size() == 0) {
            return ResponseEntity.noContent().build();
        }
        List<Bid> listOfBidWithProductId = list.stream().filter(item -> item.getProduct().getId() == notificationRequest.getProductId()).collect(Collectors.toList());
        Bid highestBid = listOfBidWithProductId.stream().max(Comparator.comparing(Bid::getAmount)).get();
        Set<Long> set = new HashSet<>(listOfBidWithProductId.size());
        listOfBidWithProductId.removeIf(p -> !set.add(p.getUser().getId()));
        for (Bid bid : listOfBidWithProductId) {
            if (bid.getUser().getId() != highestBid.getUser().getId()) {
                simpMessagingTemplate.convertAndSend("/specific/" + bid.getUser().getEmail(),notificationRequest.getMessage());
                Notification notification = new Notification(
                        notificationRequest.getMessage(),
                        ZonedDateTime.now(),
                        false,
                        bid.getUser(),
                        productRepository.findById(notificationRequest.getProductId()).get()
                );
                notificationRepository.save(notification);
            }
        }
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Object> sendNotificationWhenAuctionIsFinished(NotificationRequest notificationRequest) {
       /* List<Bid> list = bidRepository.findAll();
        if (list.size() == 0) {
            return ResponseEntity.noContent().build();
        }
        List<Bid> listOfBidWithProductId = list.stream().filter(item -> item.getProduct().getId() == notificationRequest.getProductId()).collect(Collectors.toList());
        Bid highestBid = listOfBidWithProductId.stream().max(Comparator.comparing(Bid::getAmount)).get();
        simpMessagingTemplate.convertAndSendToUser(highestBid.getUser().getEmail(), "/specific", notificationRequest.getText());
        Notification notification = new Notification(
                notificationRequest.getText(),
                notificationRequest.getDate(),
                false,
                highestBid.getUser(),
                productRepository.findById(notificationRequest.getProductId()).get()
        );
        notificationRepository.save(notification);*/
        return ResponseEntity.ok().build();
    }
}
