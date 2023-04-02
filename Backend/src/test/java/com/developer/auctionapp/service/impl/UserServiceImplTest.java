package com.developer.auctionapp.service.impl;

import com.developer.auctionapp.dto.request.UpdateUser;
import com.developer.auctionapp.dto.request.UserLoginRequest;
import com.developer.auctionapp.dto.request.UserRegisterRequest;
import com.developer.auctionapp.dto.response.AuthResponse;
import com.developer.auctionapp.dto.response.Response;
import com.developer.auctionapp.dto.response.UserResponse;
import com.developer.auctionapp.entity.User;
import com.developer.auctionapp.exception.UserAlreadyExistException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A class that contains tests for testing the methods of the UserServiceImpl class
 */

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class UserServiceImplTest {

    @Mock
    private UserServiceImpl userServiceImpl;

    /**
     * A method that tests a class method that returns all users
     */

    @Test
    void getAllUsers() {
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
                3L,
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
        Mockito.when(userServiceImpl.getAllUsers()).thenReturn(ResponseEntity.of(Optional.of(list)));
        final List<UserResponse> result = userServiceImpl.getAllUsers().getBody();
        Assertions.assertEquals(3, result.size());
        Assertions.assertEquals("user2", result.get(1).getName());
        Assertions.assertEquals("f", result.get(2).getGender());
    }

    /**
     * A method that tests a class method that register new user
     *
     * @throws UserAlreadyExistException is user with specific email alreay exists
     */

    @Test
    void registerNewUserAccount() throws UserAlreadyExistException {
        final User user = new User(
                1L,
                "user1",
                "user1surname",
                "email@gmail.com",
                "12345E",
                "12345",
                "m",
                ZonedDateTime.now().minusYears(20));
        final UserRegisterRequest userRegisterRequest = new UserRegisterRequest(
                "user1",
                "user1surname",
                "email@gmail.com",
                "12345E");
        Mockito.when(userServiceImpl.registerNewUserAccount(userRegisterRequest)).thenReturn(user);
        Assertions.assertDoesNotThrow(() -> {
            final Class<UserAlreadyExistException> userAlreadyExistExceptionClass = UserAlreadyExistException.class;
        });
        final User result = userServiceImpl.registerNewUserAccount(userRegisterRequest);
        Assertions.assertEquals("12345E", result.getPassword());
        Assertions.assertEquals("m", result.getGender());
    }

    /**
     * A method that tests a class method that logs in the user
     */

    @Test
    void loginUser() {
        final UserLoginRequest userLoginRequest = new UserLoginRequest("email@gmail.com", "12345E");
        final AuthResponse authResponse = new AuthResponse("newAccessToken");
        Mockito.when(userServiceImpl.loginUser(userLoginRequest)).thenReturn(authResponse);
        final AuthResponse response = userServiceImpl.loginUser(userLoginRequest);
        Assertions.assertEquals("newAccessToken", response.getAccessToken());
    }

    /**
     * A method that tests a class method that returns currently logged-in user
     */

    @Test
    void getCurrentUser() {
        final User user = new User(
                1L,
                "user1",
                "user1surname",
                "email@gmail.com",
                "12345E",
                "12345",
                "m",
                ZonedDateTime.now().minusYears(20));
        Mockito.when(userServiceImpl.getCurrentUser()).thenReturn(user);
        final User result = userServiceImpl.getCurrentUser();
        Assertions.assertNotNull(result);
        Assertions.assertEquals("user1surname", result.getSurname());
        Assertions.assertEquals("12345E", result.getPassword());
    }

    /**
     * A method that tests a class method that updates the data of the currently logged-in user
     */

    @Test
    void updateUser() {
        final User user = new User(
                1L,
                "newUser1",
                "user1surname",
                "email@gmail.com",
                "12345E",
                "12345",
                "m",
                ZonedDateTime.now().minusYears(20));
        final UpdateUser updateUser = new UpdateUser(
                "newUser1",
                "user1surname",
                "email@gmail.com",
                "12345E");
        Mockito.when(userServiceImpl.updateUser(updateUser)).thenReturn(user);
        final User result = userServiceImpl.updateUser(updateUser);
        Assertions.assertNotNull(result);
        Assertions.assertEquals("newUser1", result.getName());
        Assertions.assertEquals("email@gmail.com", result.getEmail());
    }

    /**
     * A method that tests a class method that deactivates the user
     */

    @Test
    void deactivateUser() {
        final Response response = new Response(200L, "deactivateUser");
        Mockito.when(userServiceImpl.deactivateUser()).thenReturn(response);
        final Response result = userServiceImpl.deactivateUser();
        Assertions.assertNotNull(result);
        Assertions.assertEquals("deactivateUser", result.getMessage());
    }
}