package com.developer.auctionapp.service.impl;

import com.developer.auctionapp.dto.request.UpdateUser;
import com.developer.auctionapp.dto.request.UserLoginRequest;
import com.developer.auctionapp.dto.request.UserRegisterRequest;
import com.developer.auctionapp.dto.response.AuthResponse;
import com.developer.auctionapp.dto.response.Response;
import com.developer.auctionapp.dto.response.UserResponse;
import com.developer.auctionapp.entity.User;
import com.developer.auctionapp.exception.UserAlreadyExistException;
import com.stripe.exception.StripeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.time.Month;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class UserServiceImplTest {

    @Mock private UserServiceImpl userServiceImpl;

    @Test
    void getAllUsers() {
        List<UserResponse> list = new ArrayList<>();
        UserResponse userResponse1 = new UserResponse(1L,"user1","user1surname","email@gmail.com", "12345E","12345", "m", ZonedDateTime.now().minusYears(20),1L);
        UserResponse userResponse2 = new UserResponse(2L,"user2","user2surname","email@gmail.com", "12345E","12345", "m", ZonedDateTime.now().minusYears(30),1L);
        UserResponse userResponse3 = new UserResponse(2L,"user3","user3surname","email@gmail.com", "12345E","12345", "f", ZonedDateTime.now().minusYears(40),1L);
        list.add(userResponse1);
        list.add(userResponse2);
        list.add(userResponse3);
        Mockito.when(userServiceImpl.getAllUsers()).thenReturn(ResponseEntity.of(Optional.of(list)));
        List<UserResponse> result = userServiceImpl.getAllUsers().getBody();
        Assertions.assertEquals(3, result.size());
        Assertions.assertEquals("user2", result.get(1).getName());
        Assertions.assertEquals("f", result.get(2).getGender());
    }

    @Test
    void registerNewUserAccount() throws UserAlreadyExistException {
        User user = new User(1L,"user1","user1surname","email@gmail.com", "12345E","12345", "m", ZonedDateTime.now().minusYears(20));
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest("user1","user1surname","email@gmail.com", "12345E");
        Mockito.when(userServiceImpl.registerNewUserAccount(userRegisterRequest)).thenReturn(user);
        Assertions.assertDoesNotThrow( () -> {
            final Class<UserAlreadyExistException> userAlreadyExistExceptionClass = UserAlreadyExistException.class;
        });
        User result  = userServiceImpl.registerNewUserAccount(userRegisterRequest);
        Assertions.assertEquals("12345E", result.getPassword());
        Assertions.assertEquals("m", result.getGender());
    }

    @Test
    void loginUser() {
        UserLoginRequest userLoginRequest = new UserLoginRequest("email@gmail.com", "12345E");
        AuthResponse authResponse = new AuthResponse("newAccessToken");
        Mockito.when(userServiceImpl.loginUser(userLoginRequest)).thenReturn(authResponse);
        AuthResponse response = userServiceImpl.loginUser(userLoginRequest);
        Assertions.assertEquals("newAccessToken", response.getAccessToken());
    }

    @Test
    void getCurrentUser() {
        User user = new User(1L,"user1","user1surname","email@gmail.com", "12345E","12345", "m", ZonedDateTime.now().minusYears(20));
        Mockito.when(userServiceImpl.getCurrentUser()).thenReturn(user);
        User result = userServiceImpl.getCurrentUser();
        Assertions.assertNotNull(result);
        Assertions.assertEquals("user1surname", result.getSurname());
        Assertions.assertEquals("12345E", result.getPassword());
    }

    @Test
    void updateUser() {
        User user = new User(1L,"newUser1","user1surname","email@gmail.com", "12345E","12345", "m", ZonedDateTime.now().minusYears(20));
        UpdateUser updateUser = new UpdateUser("newUser1","user1surname","email@gmail.com", "12345E");
        Mockito.when(userServiceImpl.updateUser(updateUser)).thenReturn(user);
        User result = userServiceImpl.updateUser(updateUser);
        Assertions.assertNotNull(result);
        Assertions.assertEquals("newUser1",result.getName());
        Assertions.assertEquals("email@gmail.com", result.getEmail());
    }

    @Test
    void deactivateUser() {
        Response response = new Response(200L, "deactivateUser");
        Mockito.when(userServiceImpl.deactivateUser()).thenReturn(response);
        Response result = userServiceImpl.deactivateUser();
        Assertions.assertNotNull(result);
        Assertions.assertEquals("deactivateUser",result.getMessage());
    }
}