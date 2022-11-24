package com.developer.auctionapp.service.impl;

import com.developer.auctionapp.dto.response.UserResponse;
import com.developer.auctionapp.entity.User;
import com.developer.auctionapp.repository.UserRepository;
import com.developer.auctionapp.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> listOfUsers = userRepository.findAll();
        List<UserResponse> list = new ArrayList<>();
        for (User res : listOfUsers){
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
}
