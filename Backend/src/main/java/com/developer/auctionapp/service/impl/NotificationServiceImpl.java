package com.developer.auctionapp.service.impl;

import com.developer.auctionapp.dto.request.NotificationRequest;
import com.developer.auctionapp.entity.Bid;
import com.developer.auctionapp.entity.Notification;
import com.developer.auctionapp.entity.User;
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

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public  ResponseEntity<Object> sendNotificationWhenUserIsOutbided(NotificationRequest notificationRequest) {
        List<Bid> list = bidRepository.findAll();
        if (list.size() == 0) {
            return ResponseEntity.noContent().build();
        }
        List<Bid> listOfBidWithProductId = list.stream().filter(item -> item.getProduct().getId() == notificationRequest.getProductId()).collect(Collectors.toList());
        Bid highestBid = listOfBidWithProductId.stream().max(Comparator.comparing(Bid::getAmount)).get();
        for (Bid bid : listOfBidWithProductId) {
            if (bid.getUser().getId() != highestBid.getUser().getId()) {
                simpMessagingTemplate.convertAndSendToUser(bid.getUser().getEmail(), "/specific", notificationRequest.getText());
                Notification notification = new Notification(
                        notificationRequest.getText(),
                        notificationRequest.getDate(),
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
        List<Bid> list = bidRepository.findAll();
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
        notificationRepository.save(notification);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Object> sendingMessage(String name) {
        System.out.println("Entered in function");
        User user = userRepository.findByEmail("epalalic1@etf.unsa.ba");
        System.out.println(name);
        simpMessagingTemplate.convertAndSendToUser(user.getEmail(), "/queue/reply", name);
        return ResponseEntity.ok().build();
    }
}
