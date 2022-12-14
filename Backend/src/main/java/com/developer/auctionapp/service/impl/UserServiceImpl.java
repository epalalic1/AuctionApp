package com.developer.auctionapp.service.impl;

import com.developer.auctionapp.dto.request.UserLoginRequest;
import com.developer.auctionapp.dto.request.UserRegisterRequest;
import com.developer.auctionapp.dto.response.AuthResponse;
import com.developer.auctionapp.dto.response.UserResponse;
import com.developer.auctionapp.entity.Role;
import com.developer.auctionapp.entity.User;
import com.developer.auctionapp.exception.UserAlreadyExistException;
import com.developer.auctionapp.repository.UserRepository;
import com.developer.auctionapp.security.JWTGenerator;
import com.developer.auctionapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>Class that implements UserService interface and we use it to comunicate with the database</p>
 */

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JWTGenerator jwtGenerator;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JWTGenerator jwtGenerator
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
    }

    /**
     * The method used to get all users from database and transform them into Data Transform Objects
     * @return list of Data Transform Objects which each of them represent one User
     */

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> listOfUsers = userRepository.findAll();
        List<UserResponse> list = new ArrayList<>();
        for (User res : listOfUsers) {
            final UserResponse item = new UserResponse(
                    res.getId(),
                    res.getName(),
                    res.getSurname(),
                    res.getEmail(),
                    res.getPassword(),
                    res.getPhone(),
                    res.getGender(),
                    res.getDateOfBirth(),
                    res.getRoles().get(0).getId());
            list.add(item);
        }
        return list;
    }

    @Override
    public User registerNewUserAccount(UserRegisterRequest userRegisterRequest) throws UserAlreadyExistException {
        if (emailExists(userRegisterRequest.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: "
                    + userRegisterRequest.getEmail());
        }
        int numberOfRows = userRepository.getNumberOfRows();
        User user = new User(
                numberOfRows + 1L,
                userRegisterRequest.getFirstName(),
                userRegisterRequest.getLastName(),
                userRegisterRequest.getEmail(),
                passwordEncoder.encode(userRegisterRequest.getPassword()),
                "",
                "",
                ZonedDateTime.parse("2000-01-01T00:00:00.147Z")
        );
        Role role = new Role(2L, "Logged in");
        user.setRoles(Collections.singletonList(role));
        userRepository.save(user);
        return user;
    }

    @Override
    public AuthResponse loginUser(UserLoginRequest userLoginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginRequest.getEmail(),
                        userLoginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new AuthResponse(token);
    }

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

}
