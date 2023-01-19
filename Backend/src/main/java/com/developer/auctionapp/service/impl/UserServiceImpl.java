package com.developer.auctionapp.service.impl;

import com.developer.auctionapp.dto.request.UpdateUser;
import com.developer.auctionapp.dto.request.UserLoginRequest;
import com.developer.auctionapp.dto.request.UserRegisterRequest;
import com.developer.auctionapp.dto.response.AuthResponse;
import com.developer.auctionapp.dto.response.Response;
import com.developer.auctionapp.dto.response.UserResponse;
import com.developer.auctionapp.entity.Product;
import com.developer.auctionapp.entity.Role;
import com.developer.auctionapp.entity.User;
import com.developer.auctionapp.exception.UserAlreadyExistException;
import com.developer.auctionapp.repository.BidRepository;
import com.developer.auctionapp.repository.ImageRepository;
import com.developer.auctionapp.repository.ProductRepository;
import com.developer.auctionapp.repository.UserRepository;
import com.developer.auctionapp.security.JWTGenerator;
import com.developer.auctionapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    private final BidRepository bidRepository;

    private final ImageRepository imageRepository;

    private final ProductRepository productRepository;


    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JWTGenerator jwtGenerator,
            BidRepository bidRepository,
            ImageRepository imageRepository,
            ProductRepository productRepository
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
        this.bidRepository = bidRepository;
        this.imageRepository = imageRepository;
        this.productRepository = productRepository;
    }

    /**
     * The method used to get all users from database and transform them into Data Transform Objects
     *
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

    /**
     * A method we use to register new user
     *
     * @param userRegisterRequest DTO object that represent user we wnat to register
     * @return successfully registered user
     * @throws UserAlreadyExistException if there is a user with the same email as the
     *                                   email of the user we want to register
     */

    @Override
    public User registerNewUserAccount(UserRegisterRequest userRegisterRequest) throws UserAlreadyExistException {
        if (emailExists(userRegisterRequest.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: "
                    + userRegisterRequest.getEmail());
        }
        User user = new User(
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

    /**
     * A method we use to loggin-in user
     *
     * @param userLoginRequest DTO object that contains all data we need to log-in user
     * @return DTO object that contains all data for logged-in  user
     */

    @Override
    public AuthResponse loginUser(UserLoginRequest userLoginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginRequest.getEmail(),
                        userLoginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new AuthResponse(token);
    }

    /**
     * A method that return currently logged-in user
     *
     * @return user that is currently logged-in
     */

    @Override
    public User getCurrentUser() {
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = userRepository.findByEmail(username);
        return user;
    }

    /**
     * A method that update currently logged-in user
     * @param updateUser DTO object with new data of the user
     * @return updated user
     */

    @Override
    public User updateUser(UpdateUser updateUser) {
        User currentUser = getCurrentUser();
        currentUser.setName(updateUser.getFirstName());
        currentUser.setSurname(updateUser.getLastName());
        currentUser.setEmail(updateUser.getEmail());
        currentUser.setPhone(updateUser.getPhone());
        userRepository.save(currentUser);
        return currentUser;
    }

    /**
     * A method that deletes the user from the database
     * as well as his bids and products that he put up for sale
     * @return a response object that contains information about whether
     *  the user was successfully deleted
     */

    @Override
    public Response deactivateUser() {
        User currentUser = getCurrentUser();
        try{
            bidRepository.deleteAllByUser(currentUser);
            List<Product> userProducts = productRepository.findByUser(currentUser);
            for(Product product : userProducts){
                imageRepository.deleteAllByProduct(product);
            }
            productRepository.deleteAllByUser(currentUser);
            userRepository.deleteById(currentUser.getId());
            return new Response(200L,"User successfully deleted");
        }
        catch (Exception e){
            return new Response(400L,"An error occurred while deleting the user");
        }

    }

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }
}
