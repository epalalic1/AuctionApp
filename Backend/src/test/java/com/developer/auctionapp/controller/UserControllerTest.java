package com.developer.auctionapp.controller;

import com.developer.auctionapp.dto.request.UpdateUser;
import com.developer.auctionapp.dto.response.Response;
import com.developer.auctionapp.dto.response.UserResponse;
import com.developer.auctionapp.entity.User;
import com.developer.auctionapp.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean private UserService userService;

    @Test
    void findAllUsers() throws Exception {
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("", "1");
        List<UserResponse> list = new ArrayList<>();
        UserResponse userResponse1 = new UserResponse(1L,"user1","user1surname","email@gmail.com", "12345E","12345", "m", ZonedDateTime.now().minusYears(20),1L);
        UserResponse userResponse2 = new UserResponse(2L,"user2","user2surname","email@gmail.com", "12345E","12345", "m", ZonedDateTime.now().minusYears(30),1L);
        UserResponse userResponse3 = new UserResponse(2L,"user3","user3surname","email@gmail.com", "12345E","12345", "f", ZonedDateTime.now().minusYears(40),1L);
        list.add(userResponse1);
        list.add(userResponse2);
        list.add(userResponse3);
        Mockito.when(userService.getAllUsers()).thenReturn(ResponseEntity.of(Optional.of(list)));
        ResultActions resultActions = mvc.perform(get("/auctionapp/user/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        ArrayList response = objectMapper.readValue(contentAsString, ArrayList.class);
        Assertions.assertEquals(3, response.size());
        Assertions.assertEquals("user1", objectMapper.convertValue(response.get(0), UserResponse.class).getName());
        Assertions.assertEquals("f", objectMapper.convertValue(response.get(2), UserResponse.class).getGender());
    }

    @Test
    void registerUser() {
    }

    @Test
    void loginUser() {
    }

    @Test
    void findCurrentUser() throws Exception {
        User user = new User(1L,"user1","user1surname","email@gmail.com", "12345E","12345", "m", ZonedDateTime.now().minusYears(20));
        Mockito.when(userService.getCurrentUser()).thenReturn(user);
        ResultActions resultActions = mvc.perform(get("/auctionapp/user/getCurrentUser")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        User response = objectMapper.readValue(contentAsString, User.class);
        Assertions.assertEquals("user1", objectMapper.convertValue(response, UserResponse.class).getName());
        Assertions.assertEquals("email@gmail.com", objectMapper.convertValue(response, UserResponse.class).getEmail());
    }

    @Test
    void updateLoggedInUser() throws Exception {
        User user = new User(1L,"newUser1","user1surname","email@gmail.com", "12345E","12345", "m", ZonedDateTime.now().minusYears(20));
        UpdateUser updateUser = new UpdateUser("newUser1","user1surname","email@gmail.com", "12345E");
        Mockito.when(userService.updateUser(updateUser)).thenReturn(user);
        ResultActions resultActions  = mvc.perform( MockMvcRequestBuilders
                        .put("/auctionapp/user/updateUser")
                        .content(objectMapper.writeValueAsString(updateUser))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        Response newReponse = objectMapper.readValue(contentAsString, Response.class);
        System.out.println(newReponse);
    }

    @Test
    void deleteUser() throws Exception {
        Response response = new Response(200L, "deactivateUser");
        Mockito.when(userService.deactivateUser()).thenReturn(response);
        ResultActions resultActions = mvc.perform( MockMvcRequestBuilders.delete("/auctionapp/user/deactivateUser") )
                .andExpect(status().isOk());
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        Response newReponse = objectMapper.readValue(contentAsString, Response.class);
        Assertions.assertEquals(200L, objectMapper.convertValue(response, Response.class).getStatusCode());
        Assertions.assertEquals("deactivateUser", objectMapper.convertValue(response, Response.class).getMessage());


    }
}