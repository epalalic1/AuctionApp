package com.developer.auctionapp.controller;

import com.developer.auctionapp.dto.response.NotificationResponse;
import com.developer.auctionapp.service.NotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * A class that contains tests for testing the Notification controller
 */

@RunWith(SpringRunner.class)
@WebMvcTest(NotificationController.class)
@AutoConfigureMockMvc(addFilters = false)
class NotificationControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private NotificationService notificationService;

    /**
     * A method that tests the controller method that returns all notifications for specific user
     *
     * @throws Exception if a method "perform" of MockMvc throws an exception
     */

    @Test
    void getNotificationsByUserIdMethod() throws Exception {
        final List<NotificationResponse> list = new ArrayList<>();
        final NotificationResponse notificationResponse1 = new NotificationResponse("message1", 1L, 5L, false);
        final NotificationResponse notificationResponse2 = new NotificationResponse("message2", 1L, 4L, false);
        final NotificationResponse notificationResponse3 = new NotificationResponse("message3", 1L, 1L, false);
        final NotificationResponse notificationResponse4 = new NotificationResponse("message4", 1L, 2L, false);
        list.add(notificationResponse1);
        list.add(notificationResponse2);
        list.add(notificationResponse3);
        list.add(notificationResponse4);
        Mockito.when(notificationService.getNotificationsByUserId(1L)).thenReturn(ResponseEntity.of(Optional.of(list)));
        final ResultActions resultActions = mvc.perform(get("/auctionapp/getNotificationsForUser")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        final MvcResult result = resultActions.andReturn();
        final String contentAsString = result.getResponse().getContentAsString();
        final ArrayList response = objectMapper.readValue(contentAsString, ArrayList.class);
        Assertions.assertEquals(4, response.size());
        Assertions.assertEquals("message1", objectMapper.convertValue(response.get(0), NotificationResponse.class).getMessage());
        Assertions.assertEquals(5L, objectMapper.convertValue(response.get(0), NotificationResponse.class).getProductId());
    }
}