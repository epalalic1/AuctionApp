package com.developer.auctionapp.controller;

import com.developer.auctionapp.dto.request.UpdateUser;
import com.developer.auctionapp.dto.request.UserLoginRequest;
import com.developer.auctionapp.dto.request.UserRegisterRequest;
import com.developer.auctionapp.dto.response.AuthResponse;
import com.developer.auctionapp.dto.response.Response;
import com.developer.auctionapp.dto.response.UserResponse;
import com.developer.auctionapp.entity.User;
import com.developer.auctionapp.service.UserService;
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
import org.springframework.util.LinkedMultiValueMap;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 */

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    /**
     * A method that tests the controller method that returns all registered users
     *
     * @throws Exception
     */

    @Test
    void findAllUsers() throws Exception {
        final LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("", "1");
        final List<UserResponse> list = new ArrayList<>();
        final UserResponse userResponse1 = new UserResponse(
                1L,
                "user1",
                "user1surname",
                "email@gmail.com",
                "12345E",
                "12345",
                "m",
                ZonedDateTime.now().minusYears(20),
                1L);
        final UserResponse userResponse2 = new UserResponse(
                2L,
                "user2",
                "user2surname",
                "email@gmail.com",
                "12345E",
                "12345",
                "m",
                ZonedDateTime.now().minusYears(30),
                1L);
        final UserResponse userResponse3 = new UserResponse(
                2L,
                "user3",
                "user3surname",
                "email@gmail.com",
                "12345E",
                "12345",
                "f",
                ZonedDateTime.now().minusYears(40),
                1L);
        list.add(userResponse1);
        list.add(userResponse2);
        list.add(userResponse3);
        Mockito.when(userService.getAllUsers()).thenReturn(ResponseEntity.of(Optional.of(list)));
        final ResultActions resultActions = mvc.perform(get("/auctionapp/user/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        final MvcResult result = resultActions.andReturn();
        final String contentAsString = result.getResponse().getContentAsString();
        final ArrayList response = objectMapper.readValue(contentAsString, ArrayList.class);
        Assertions.assertEquals(3, response.size());
        Assertions.assertEquals("user1", objectMapper.convertValue(response.get(0), UserResponse.class).getName());
        Assertions.assertEquals("f", objectMapper.convertValue(response.get(2), UserResponse.class).getGender());
    }

    /**
     * A method that tests the controller method that register the user
     *
     * @throws Exception if a method "perform" of MockMvc throws an exception
     */

    @Test
    void registerUser() throws Exception {
        final User user = new User(
                1L,
                "user1",
                "user1surname",
                "email@gmail.com",
                "12345E",
                "12345",
                "m",
                ZonedDateTime.now().minusYears(20));
        final UserRegisterRequest userRegisterRequest = new UserRegisterRequest("user1", "user1surname", "email@gmail.com", "12345E");
        Mockito.when(userService.registerNewUserAccount(userRegisterRequest)).thenReturn(user);
        final String json = new ObjectMapper().writeValueAsString(userRegisterRequest);
        mvc.perform(MockMvcRequestBuilders.post("/auctionapp/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    /**
     * A method that tests the controller method that logs in the user
     *
     * @throws Exception if a method "perform" of MockMvc throws an exception
     */

    @Test
    void loginUser() throws Exception {
        final UserLoginRequest userLoginRequest = new UserLoginRequest("email@gmail.com", "12345E");
        final AuthResponse authResponse = new AuthResponse("newAccessToken");
        Mockito.when(userService.loginUser(userLoginRequest)).thenReturn(authResponse);
        final String json = new ObjectMapper().writeValueAsString(userLoginRequest);
        mvc.perform(MockMvcRequestBuilders.post("/auctionapp/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    /**
     * A method that tests the controller method that find currently logged in user
     *
     * @throws Exception if a method "perform" of MockMvc throws an exception
     */

    @Test
    void findCurrentUser() throws Exception {
        final User user = new User(
                1L,
                "user1",
                "user1surname",
                "email@gmail.com",
                "12345E",
                "12345",
                "m",
                ZonedDateTime.now().minusYears(20));
        Mockito.when(userService.getCurrentUser()).thenReturn(user);
        final ResultActions resultActions = mvc.perform(get("/auctionapp/user/getCurrentUser")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        final MvcResult result = resultActions.andReturn();
        final String contentAsString = result.getResponse().getContentAsString();
        final User response = objectMapper.readValue(contentAsString, User.class);
        Assertions.assertEquals("user1", objectMapper.convertValue(response, UserResponse.class).getName());
        Assertions.assertEquals("email@gmail.com", objectMapper.convertValue(response, UserResponse.class).getEmail());
    }

    /**
     * A method that tests the controller method that updates the data of currently logged-in user
     *
     * @throws Exception if a method "perform" of MockMvc throws an exception
     */

    @Test
    void updateLoggedInUser() throws Exception {
        final User user = new User(
                1L,
                "newUser1",
                "user1surname",
                "email@gmail.com",
                "12345E",
                "12345",
                "m",
                ZonedDateTime.now().minusYears(20));
        final UpdateUser updateUser = new UpdateUser("newUser1", "user1surname", "email@gmail.com", "12345E");
        Mockito.when(userService.updateUser(updateUser)).thenReturn(user);
        final ResultActions resultActions = mvc.perform(MockMvcRequestBuilders
                        .put("/auctionapp/user/updateUser")
                        .content(objectMapper.writeValueAsString(updateUser))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /**
     * A method that tests the controller method that deletes the user
     *
     * @throws Exception if a method "perform" of MockMvc throws an exception
     */

    @Test
    void deleteUser() throws Exception {
        final Response response = new Response(200L, "deactivateUser");
        Mockito.when(userService.deactivateUser()).thenReturn(response);
        final ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.delete("/auctionapp/user/deactivateUser"))
                .andExpect(status().isOk());
        final MvcResult result = resultActions.andReturn();
        final String contentAsString = result.getResponse().getContentAsString();
        final Response newReponse = objectMapper.readValue(contentAsString, Response.class);
        Assertions.assertEquals(200L, objectMapper.convertValue(response, Response.class).getStatusCode());
        Assertions.assertEquals("deactivateUser", objectMapper.convertValue(response, Response.class).getMessage());
    }
}