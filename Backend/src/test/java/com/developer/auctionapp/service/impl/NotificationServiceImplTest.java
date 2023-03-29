package com.developer.auctionapp.service.impl;

import com.developer.auctionapp.dto.request.NotificationRequest;
import com.developer.auctionapp.dto.response.NotificationResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class NotificationServiceImplTest {

    @Mock private NotificationServiceImpl notificationServiceImpl;

    @Test
    void sendNotificationWhenUserIsOutbided() {
        NotificationRequest notificationRequest = new NotificationRequest("message",1L,5L,false);
        ResponseEntity<Object> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        Mockito.when(notificationServiceImpl.sendNotificationWhenUserIsOutbided(notificationRequest)).thenReturn(responseEntity);
        ResponseEntity result = notificationServiceImpl.sendNotificationWhenUserIsOutbided(notificationRequest);
        Assertions.assertEquals(200,result.getStatusCodeValue());
    }

    @Test
    void sendNotificationWhenAuctionIsFinished() {
        String message = "newMessage";
        ResponseEntity<Object> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        Mockito.when(notificationServiceImpl.sendNotificationWhenAuctionIsFinished(message)).thenReturn(responseEntity);
        ResponseEntity result = notificationServiceImpl.sendNotificationWhenAuctionIsFinished(message);
        Assertions.assertEquals(200,result.getStatusCodeValue());
    }

    @Test
    void getNotificationsByUserId() {
        List<NotificationResponse> list = new ArrayList<>();
        NotificationResponse notificationResponse1 = new NotificationResponse("message1",1L,5L,false);
        NotificationResponse notificationResponse2 = new NotificationResponse("message2",1L,4L,false);
        NotificationResponse notificationResponse3 = new NotificationResponse("message3",1L,1L,false);
        NotificationResponse notificationResponse4 = new NotificationResponse("message4",1L,2L,false);
        list.add(notificationResponse1);
        list.add(notificationResponse2);
        list.add(notificationResponse3);
        list.add(notificationResponse4);
        Mockito.when(notificationServiceImpl.getNotificationsByUserId(1L)).thenReturn(ResponseEntity.of(Optional.of(list)));
        ArrayList result = (ArrayList) notificationServiceImpl.getNotificationsByUserId(1L).getBody();
        assert result != null;
        Assertions.assertEquals(4, result.size());
        Assertions.assertTrue(result.contains(notificationResponse1));
        Assertions.assertTrue(result.contains(notificationResponse3));
    }
}