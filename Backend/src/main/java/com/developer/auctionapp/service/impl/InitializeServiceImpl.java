package com.developer.auctionapp.service.impl;

import com.developer.auctionapp.dto.response.Response;
import com.developer.auctionapp.entity.*;
import com.developer.auctionapp.repository.*;
import com.developer.auctionapp.service.InitializeService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Class that implements InitializeService  interface and we use it to comunicate with the database
 * to seed the database with the initial data</p>
 *
 */

@Service
@Transactional(rollbackFor=Exception.class)
public class InitializeServiceImpl implements InitializeService {

    private final CategoryRepository categoryRepository;

    private final SubcategoryRepository subcategoryRepository;

    private final ProductRepository productRepository;

    private final BidRepository bidRepository;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final ImageRepository imageRepository;

    private final PasswordEncoder passwordEncoder;

    public InitializeServiceImpl(
            CategoryRepository categoryRepository,
            SubcategoryRepository subcategoryRepository,
            ProductRepository productRepository,
            BidRepository bidRepository,
            UserRepository userRepository,
            RoleRepository roleRepository,
            ImageRepository imageRepository,
            PasswordEncoder passwordEncoder) {
        this.categoryRepository = categoryRepository;
        this.subcategoryRepository = subcategoryRepository;
        this.productRepository = productRepository;
        this.bidRepository = bidRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.imageRepository = imageRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private String initializeCategory = "";

    private String initializeSubcategory = "";

    private String initializeProduct = "";

    private String initializeBid = "";

    private String initializeUser = "";

    private String initializeImage = "";

    /**
     * A method that inserts data into the Category table after checking that no data already exists in the table
     */

    @Override
    public Boolean initializeCategoryTable() {
        System.out.println("Usli smo ovdje  druga funckija");
        if (categoryRepository.findAll().size() != 0) {
            return true;
        }
        System.out.println("ok je ovdje");
        List<Category> listOfCategories = new ArrayList<>();
        Category category1 = new Category(1L, "Men");
        Category category2 = new Category(2L, "Woman");
        Category category3 = new Category(3L, "Kids");
        Category category4 = new Category(4L, "Home");
        Category category5 = new Category(5L, "Art");
        Category category6 = new Category(6L, "Accessories");
        Category category7 = new Category(7L, "Copmputers");
        Category category8 = new Category(8L, "Mobile");
        Category category9 = new Category(9L, "Sportwear");
        Category category10 = new Category(10L, "Electronics");
        listOfCategories.add(category1);
        listOfCategories.add(category2);
        listOfCategories.add(category3);
        listOfCategories.add(category4);
        listOfCategories.add(category5);
        listOfCategories.add(category6);
        listOfCategories.add(category7);
        listOfCategories.add(category8);
        listOfCategories.add(category9);
        listOfCategories.add(category10);
        try {
            categoryRepository.saveAll(listOfCategories);
        } catch (Exception e) {
            initializeCategory = "An error occurred while initializing the Category table ";
        }
        return false;
    }

    /**
     * A method that inserts data into the Subcategory table after checking that no data already exists in the table
     */

    @Override
    public void initializeSubcategoryTable() {
        if (subcategoryRepository.findAll().size() != 0) {
            return;
        }
        List<Subcategory> listOfSubcategories = new ArrayList<>();
        Subcategory subcategory1 = new Subcategory(1L, "Bags", new Category(2L, "Woman"));
        Subcategory subcategory2 = new Subcategory(2L, "Accessories", new Category(2L, "Woman"));
        Subcategory subcategory3 = new Subcategory(3L, "Jewlery", new Category(2L, "Woman"));
        Subcategory subcategory4 = new Subcategory(4L, "Shoes", new Category(2L, "Woman"));
        Subcategory subcategory5 = new Subcategory(5L, "Sportwear", new Category(1L, "Men"));
        Subcategory subcategory6 = new Subcategory(6L, "Chairs", new Category(4L, "Home"));
        Subcategory subcategory7 = new Subcategory(7L, "Tables", new Category(4L, "Home"));
        Subcategory subcategory8 = new Subcategory(8L, "Toys", new Category(3L, "Kids"));
        Subcategory subcategory9 = new Subcategory(9L, "Books", new Category(3L, "Kids"));
        listOfSubcategories.add(subcategory1);
        listOfSubcategories.add(subcategory2);
        listOfSubcategories.add(subcategory3);
        listOfSubcategories.add(subcategory4);
        listOfSubcategories.add(subcategory5);
        listOfSubcategories.add(subcategory6);
        listOfSubcategories.add(subcategory7);
        listOfSubcategories.add(subcategory8);
        listOfSubcategories.add(subcategory9);
        try {
            subcategoryRepository.saveAll(listOfSubcategories);
        } catch (Exception e) {
            initializeSubcategory = "An error occurred while initializing the Subcategory table ";
        }
    }

    /**
     * A method that inserts data into the Product table after checking that no data already exists in the table
     */


    @Override
    public void initializeProductTable() {
        if (productRepository.findAll().size() != 0) {
            return;
        }
        List<Product> listOfProducts = new ArrayList<>();
        Product product1 = new Product(1L,
                "Blue bag",
                ZonedDateTime.parse("2022-12-07T00:00:00.147Z"),
                ZonedDateTime.parse("2023-11-23T00:00:00.147Z"),
                15L,
                "details",
                false,
                15L,
                new Subcategory(1L, "Bags", new Category(2L, "Woman")),
                new User(2L, "user2", "user2", "user2",
                        passwordEncoder.encode("user2"), "user2", "user2",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z")));
        Product product2 = new Product(2L,
                "Patrick",
                ZonedDateTime.parse("2022-12-07T00:00:00.147Z"),
                ZonedDateTime.parse("2023-11-22T00:00:00.147Z"),
                150L,
                "details",
                false,
                150L,
                new Subcategory(8L, "Toys", new Category(3L, "Kids")),
                new User(2L, "user2", "user2", "user2",
                        passwordEncoder.encode("user2"), "user2", "user2",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z")));
        Product product3 = new Product(3L,
                "Necklace",
                ZonedDateTime.parse("2022-12-07T00:00:00.147Z"),
                ZonedDateTime.parse("2023-11-23T00:00:00.147Z"),
                23L,
                "details",
                false,
                23L,
                new Subcategory(2L, "Accessories", new Category(2L, "Woman")),
                new User(2L, "user2", "user2", "user2",
                        passwordEncoder.encode("user2"), "user2", "user2",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z")));
        Product product4 = new Product(4L,
                "Blue Shoes",
                ZonedDateTime.parse("2022-12-07T00:00:00.147Z"),
                ZonedDateTime.parse("2023-11-23T00:00:00.147Z"),
                23L,
                "details",
                false,
                23L,
                new Subcategory(4L, "Shoes", new Category(2L, "Woman")),
                new User(2L, "user2", "user2", "user2",
                        passwordEncoder.encode("user2"), "user2", "user2",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z")));
        Product product5 = new Product(5L,
                "Red shirt",
                ZonedDateTime.parse("2022-12-07T00:00:00.147Z"),
                ZonedDateTime.parse("2023-11-23T00:00:00.147Z"),
                26L,
                "details",
                false,
                26L,
                new Subcategory(5L, "Sportwear", new Category(1L, "Men")),
                new User(2L, "user2", "user2", "user2",
                        passwordEncoder.encode("user2"), "user2", "user2",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z")));
        Product product6 = new Product(6L,
                "White Necklace",
                ZonedDateTime.parse("2022-12-07T00:00:00.147Z"),
                ZonedDateTime.parse("2023-11-23T00:00:00.147Z"),
                13L,
                "details",
                false,
                13L,
                new Subcategory(2L, "Accessories", new Category(2L, "Woman")),
                new User(2L, "user2", "user2", "user2",
                        passwordEncoder.encode("user2"), "user2", "user2",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z")));
        Product product7 = new Product(7L,
                "Red Bag",
                ZonedDateTime.parse("2022-12-07T00:00:00.147Z"),
                ZonedDateTime.parse("2023-11-23T00:00:00.147Z"),
                10L,
                "details",
                false,
                10L,
                new Subcategory(1L, "Bags", new Category(2L, "Woman")),
                new User(2L, "user2", "user2", "user2",
                        passwordEncoder.encode("user2"), "user2", "user2",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z")));
        Product product8 = new Product(8L,
                "Black toy",
                ZonedDateTime.parse("2022-12-07T00:00:00.147Z"),
                ZonedDateTime.parse("2023-11-23T00:00:00.147Z"),
                14L,
                "details",
                false,
                14L,
                new Subcategory(8L, "Toys", new Category(3L, "Kids")),
                new User(2L, "user2", "user2", "user2",
                        passwordEncoder.encode("user2"), "user2", "user2",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z")));
        Product product9 = new Product(9L,
                "Black table",
                ZonedDateTime.parse("2022-12-07T00:00:00.147Z"),
                ZonedDateTime.parse("2023-11-23T00:00:00.147Z"),
                14L,
                "details",
                false,
                14L,
                new Subcategory(7L, "Tables", new Category(4L, "Home")),
                new User(2L, "user2", "user2", "user2",
                        passwordEncoder.encode("user2"), "user2", "user2",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z")));
        Product product10 = new Product(10L,
                "Red chair",
                ZonedDateTime.parse("2022-12-07T00:00:00.147Z"),
                ZonedDateTime.parse("2023-11-23T00:00:00.147Z"),
                15L,
                "details",
                false,
                15L,
                new Subcategory(6L, "Chairs", new Category(4L, "Home")),
                new User(2L, "user2", "user2", "user2",
                        passwordEncoder.encode("user2"), "user2", "user2",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z")));


        Product product11 = new Product(11L,
                "Blue chair",
                ZonedDateTime.parse("2021-11-18T00:00:00.147Z"),
                ZonedDateTime.parse("2022-12-10T00:00:00.147Z"),
                25L,
                "details",
                false,
                25L,
                new Subcategory(6L, "Chairs", new Category(4L, "Home")),
                new User(1L, "user1", "user1", "user1",
                        passwordEncoder.encode("user1"), "user1", "user1",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z")));
        Product product12 = new Product(12L,
                "Sofa",
                ZonedDateTime.parse("2021-11-19T00:00:00.147Z"),
                ZonedDateTime.parse("2022-12-12T00:00:00.147Z"),
                25L,
                "details",
                false,
                25L,
                new Subcategory(6L, "Chairs", new Category(4L, "Home")),
                new User(1L, "user1", "user1", "user1",
                        passwordEncoder.encode("user1"), "user1", "user1",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z")));
        Product product13 = new Product(13L,
                "Table",
                ZonedDateTime.parse("2021-11-17T00:00:00.147Z"),
                ZonedDateTime.parse("2022-12-10T00:00:00.147Z"),
                24L,
                "details",
                false,
                24L,
                new Subcategory(6L, "Chairs", new Category(4L, "Home")),
                new User(1L, "user1", "user1", "user1",
                        passwordEncoder.encode("user1"), "user1", "user1",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z")));
        Product product14 = new Product(14L,
                "Chandelier",
                ZonedDateTime.parse("2021-11-19T00:00:00.147Z"),
                ZonedDateTime.parse("2022-12-10T00:00:00.147Z"),
                17L,
                "details",
                false,
                17L,
                new Subcategory(6L, "Chairs", new Category(4L, "Home")),
                new User(1L, "user1", "user1", "user1",
                        passwordEncoder.encode("user1"), "user1", "user1",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z")));
        Product product15 = new Product(15L,
                "Curtains",
                ZonedDateTime.parse("2021-11-18T00:00:00.147Z"),
                ZonedDateTime.parse("2022-12-12T00:00:00.147Z"),
                12L,
                "details",
                false,
                12L,
                new Subcategory(6L, "Chairs", new Category(4L, "Home")),
                new User(1L, "user1", "user1", "user1",
                        passwordEncoder.encode("user1"), "user1", "user1",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z")));
        Product product16 = new Product(16L,
                "Carpet",
                ZonedDateTime.parse("2021-11-19T00:00:00.147Z"),
                ZonedDateTime.parse("2022-12-13T00:00:00.147Z"),
                36L,
                "details",
                false,
                36L,
                new Subcategory(6L, "Chairs", new Category(4L, "Home")),
                new User(1L, "user1", "user1", "user1",
                        passwordEncoder.encode("user1"), "user1", "user1",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z")));
        Product product17 = new Product(17L,
                "Night table",
                ZonedDateTime.parse("2021-11-19T00:00:00.147Z"),
                ZonedDateTime.parse("2022-12-14T00:00:00.147Z"),
                16L,
                "details",
                false,
                16L,
                new Subcategory(6L, "Chairs", new Category(4L, "Home")),
                new User(1L, "user1", "user1", "user1",
                        passwordEncoder.encode("user1"), "user1", "user1",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z")));
        Product product18 = new Product(18L,
                "Pillow",
                ZonedDateTime.parse("2021-11-18T00:00:00.147Z"),
                ZonedDateTime.parse("2022-12-09T00:00:00.147Z"),
                20L,
                "details",
                false,
                20L,
                new Subcategory(6L, "Chairs", new Category(4L, "Home")),
                new User(1L, "user1", "user1", "user1",
                        passwordEncoder.encode("user1"), "user1", "user1",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z")));
        Product product19 = new Product(19L,
                "Blanket",
                ZonedDateTime.parse("2021-11-19T00:00:00.147Z"),
                ZonedDateTime.parse("2022-12-10T00:00:00.147Z"),
                7L,
                "details",
                false,
                7L,
                new Subcategory(6L, "Chairs", new Category(4L, "Home")),
                new User(1L, "user1", "user1", "user1",
                        passwordEncoder.encode("user1"), "user1", "user1",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z")));
        Product product20 = new Product(20L,
                "Shelf",
                ZonedDateTime.parse("2021-11-19T00:00:00.147Z"),
                ZonedDateTime.parse("2022-12-13T00:00:00.147Z"),
                10L,
                "details",
                false,
                10L,
                new Subcategory(6L, "Chairs", new Category(4L, "Home")),
                new User(1L, "user1", "user1", "user1",
                        passwordEncoder.encode("user1"), "user1", "user1",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z")));
        listOfProducts.add(product1);
        listOfProducts.add(product2);
        listOfProducts.add(product3);
        listOfProducts.add(product4);
        listOfProducts.add(product5);
        listOfProducts.add(product6);
        listOfProducts.add(product7);
        listOfProducts.add(product8);
        listOfProducts.add(product9);
        listOfProducts.add(product10);
        listOfProducts.add(product11);
        listOfProducts.add(product12);
        listOfProducts.add(product13);
        listOfProducts.add(product14);
        listOfProducts.add(product15);
        listOfProducts.add(product16);
        listOfProducts.add(product17);
        listOfProducts.add(product18);
        listOfProducts.add(product19);
        listOfProducts.add(product20);
        try {
            productRepository.saveAll(listOfProducts);
        } catch (Exception e) {
            initializeProduct = "An error occurred while initializing the Product table ";
        }
    }

    /**
     * A method that inserts data into the Bid table after checking that no data already exists in the table
     */

    @Override
    public void initializeBidTable() {
        if (bidRepository.findAll().size() != 0) {
            return;
        }
        List<Bid> listOfBids = new ArrayList<>();
        Bid bid1 = new Bid(1L, 120L, ZonedDateTime.parse("2022-06-15T00:00:00.147Z"),
                new Product(3L,
                        "Necklace",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z"),
                        ZonedDateTime.parse("2022-11-04T00:00:00.147Z"),
                        23L,
                        "details",
                        false,
                        23L,
                        new Subcategory(2L, "Accessories", new Category(2L, "Woman")),
                        new User(2L, "user2", "user2", "user2",
                                passwordEncoder.encode("user2"), "user2", "user2",
                                ZonedDateTime.parse("2022-04-12T00:00:00.147Z"))),
                new User(1L, "user1", "user1", "user1",
                        "user1", "user1", "user1",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z")));
        Bid bid2 = new Bid(2L, 150L, ZonedDateTime.parse("2022-06-15T00:00:00.147Z"),
                new Product(3L,
                        "Necklace",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z"),
                        ZonedDateTime.parse("2022-11-04T00:00:00.147Z"),
                        23L,
                        "details",
                        false,
                        23L,
                        new Subcategory(2L, "Accessories", new Category(2L, "Woman")),
                        new User(2L, "user2", "user2", "user2",
                                passwordEncoder.encode("user2"), "user2", "user2",
                                ZonedDateTime.parse("2022-04-12T00:00:00.147Z"))),
                new User(1L, "user1", "user1", "user1",
                        "user1", "user1", "user1",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z")));
        Bid bid3 = new Bid(3L, 160L, ZonedDateTime.parse("2022-06-15T00:00:00.147Z"),
                new Product(3L,
                        "Necklace",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z"),
                        ZonedDateTime.parse("2022-11-04T00:00:00.147Z"),
                        23L,
                        "details",
                        false,
                        23L,
                        new Subcategory(2L, "Accessories", new Category(2L, "Woman")),
                        new User(2L, "user2", "user2", "user2",
                                passwordEncoder.encode("user2"), "user2", "user2",
                                ZonedDateTime.parse("2022-04-12T00:00:00.147Z"))),
                new User(1L, "user1", "user1", "user1",
                        "user1", "user1", "user1",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z")));
        Bid bid4 = new Bid(4L, 180L, ZonedDateTime.parse("2022-06-15T00:00:00.147Z"),
                new Product(3L,
                        "Necklace",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z"),
                        ZonedDateTime.parse("2022-11-04T00:00:00.147Z"),
                        23L,
                        "details",
                        false,
                        23L,
                        new Subcategory(2L, "Accessories", new Category(2L, "Woman")),
                        new User(2L, "user2", "user2", "user2",
                                passwordEncoder.encode("user2"), "user2", "user2",
                                ZonedDateTime.parse("2022-04-12T00:00:00.147Z"))),
                new User(1L, "user1", "user1", "user1",
                        "user1", "user1", "user1",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z")));
        listOfBids.add(bid1);
        listOfBids.add(bid2);
        listOfBids.add(bid3);
        listOfBids.add(bid4);
        try {
            bidRepository.saveAll(listOfBids);
        } catch (Exception e) {
            initializeBid = "An error occurred while initializing the Bid table ";
        }
    }

    /**
     * A method that inserts data into the User and Role tables after checking that no data already exists in the table
     */

    @Override
    public void initializeUserTable() {
        if (userRepository.findAll().size() != 0 && roleRepository.findAll().size() != 0) {
            return;
        }
        List<User> listOfUsers = new ArrayList<>();
        User user1 = new User(1L, "user1", "user1", "user1",
                passwordEncoder.encode("user1"), "user1", "user1",
                ZonedDateTime.parse("2022-04-12T00:00:00.147Z"));
        User user2 = new User(2L, "user2", "user2", "user2",
                passwordEncoder.encode("user2"), "user2", "user2",
                ZonedDateTime.parse("2022-04-12T00:00:00.147Z"));
        User user3 = new User(3L, "user3", "user3", "user3",
                passwordEncoder.encode("user3"), "user3", "use3",
                ZonedDateTime.parse("2022-04-12T00:00:00.147Z"));
        listOfUsers.add(user1);
        listOfUsers.add(user2);
        listOfUsers.add(user3);
        try {
            this.userRepository.saveAll(listOfUsers);
            List<Role> listOfRoles = new ArrayList<>();
            Role role1 = new Role(1L, "Admin");
            Role role2 = new Role(2L, "Logged in");
            listOfRoles.add(role1);
            listOfRoles.add(role2);
            this.roleRepository.saveAll(listOfRoles);
            List<Role> a = new ArrayList<>();
            a.add(role1);
            List<Role> b = new ArrayList<>();
            b.add(role2);
            user1.setRoles(a);
            user2.setRoles(b);
            user3.setRoles(a);
            this.userRepository.save(user1);
            this.userRepository.save(user2);
            this.userRepository.save(user3);
        } catch (Exception e) {
            initializeUser = "An error occurred while initializing the User-Role tables ";
        }

    }

    /**
     * A method that inserts data into the Image table after checking that no data already exists in the table
     */

    @Override
    public void initializeImageTable() {
        if (imageRepository.findAll().size() != 0) {
            return;
        }
        List<Image> listOfImages = new ArrayList<>();
        Image image1 = new Image(1L, "necklace.jpg", new Product(3L,
                "Necklace",
                ZonedDateTime.parse("2022-04-12T00:00:00.147Z"),
                ZonedDateTime.parse("2022-11-04T00:00:00.147Z"),
                23L,
                "details",
                false,
                23L,
                new Subcategory(2L, "Accessories", new Category(2L, "Woman")),
                new User(2L, "user2", "user2", "user2",
                        passwordEncoder.encode("user2"), "user2", "user2",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z"))));

        Image image2 = new Image(2L, "redShirt.jpg", new Product(5L,
                "Red shirt",
                ZonedDateTime.parse("2022-06-15T00:00:00.147Z"),
                ZonedDateTime.parse("2022-11-07T00:00:00.147Z"),
                26L,
                "details",
                false,
                26L,
                new Subcategory(5L, "Sportwear", new Category(1L, "Men")),
                new User(2L, "user2", "user2", "user2",
                        passwordEncoder.encode("user2"), "user2", "user2",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z"))));

        Image image3 = new Image(3L, "redBag.jpg", new Product(7L,
                "Red Bag",
                ZonedDateTime.parse("2022-01-01T00:00:00.147Z"),
                ZonedDateTime.parse("2022-11-06T00:00:00.147Z"),
                10L,
                "details",
                false,
                10L,
                new Subcategory(1L, "Bags", new Category(2L, "Woman")),
                new User(2L, "user2", "user2", "user2",
                        passwordEncoder.encode("user2"), "user2", "user2",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z"))));

        Image image4 = new Image(4L, "redChair.jpg", new Product(10L,
                "Red chair",
                ZonedDateTime.parse("2022-05-12T00:00:00.147Z"),
                ZonedDateTime.parse("2022-11-07T00:00:00.147Z"),
                15L,
                "details",
                false,
                15L,
                new Subcategory(6L, "Chairs", new Category(4L, "Home")),
                new User(2L, "user2", "user2", "user2",
                        passwordEncoder.encode("user2"), "user2", "user2",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z"))));

        Image image5 = new Image(5L, "blueShoes.jpg", new Product(4L,
                "Blue Shoes",
                ZonedDateTime.parse("2022-01-01T00:00:00.147Z"),
                ZonedDateTime.parse("2022-11-04T00:00:00.147Z"),
                23L,
                "details",
                false,
                23L,
                new Subcategory(4L, "Shoes", new Category(2L, "Woman")),
                new User(2L, "user2", "user2", "user2",
                        passwordEncoder.encode("user2"), "user2", "user2",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z"))));

        Image image6 = new Image(6L, "whiteNecklace.jpg", new Product(6L,
                "White Necklace",
                ZonedDateTime.parse("2022-02-12T00:00:00.147Z"),
                ZonedDateTime.parse("2022-11-05T00:00:00.147Z"),
                13L,
                "details",
                false,
                13L,
                new Subcategory(2L, "Accessories", new Category(2L, "Woman")),
                new User(2L, "user2", "user2", "user2",
                        passwordEncoder.encode("user2"), "user2", "user2",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z"))));
        Image image7 = new Image(7L, "blackToy.jpeg", new Product(8L,
                "Black toy",
                ZonedDateTime.parse("2022-05-01T00:00:00.147Z"),
                ZonedDateTime.parse("2022-11-06T00:00:00.147Z"),
                14L,
                "details",
                false,
                14L,
                new Subcategory(8L, "Toys", new Category(3L, "Kids")),
                new User(2L, "user2", "user2", "user2",
                        passwordEncoder.encode("user2"), "user2", "user2",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z"))));

        Image image8 = new Image(8L, "blueChair.jpg", new Product(11L,
                "Blue chair",
                ZonedDateTime.parse("2022-11-18T00:00:00.147Z"),
                ZonedDateTime.parse("2023-11-23T00:00:00.147Z"),
                25L,
                "details",
                false,
                25L,
                new Subcategory(6L, "Chairs", new Category(4L, "Home")),
                new User(1L, "user1", "user1", "user1",
                        passwordEncoder.encode("user1"), "user1", "user1",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z"))));

        Image image9 = new Image(9L, "sofa.jpg", new Product(12L,
                "Sofa",
                ZonedDateTime.parse("2022-11-19T00:00:00.147Z"),
                ZonedDateTime.parse("2023-11-23T00:00:00.147Z"),
                25L,
                "details",
                false,
                25L,
                new Subcategory(6L, "Chairs", new Category(4L, "Home")),
                new User(1L, "user1", "user1", "user1",
                        passwordEncoder.encode("user1"), "user1", "user1",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z"))));

        Image image10 = new Image(10L, "table.jpg", new Product(13L,
                "Table",
                ZonedDateTime.parse("2022-11-17T00:00:00.147Z"),
                ZonedDateTime.parse("2023-11-23T00:00:00.147Z"),
                24L,
                "details",
                false,
                24L,
                new Subcategory(6L, "Chairs", new Category(4L, "Home")),
                new User(1L, "user1", "user1", "user1",
                        passwordEncoder.encode("user1"), "user1", "user1",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z"))));

        Image image11 = new Image(11L, "chandelier.jpg", new Product(14L,
                "Chandelier",
                ZonedDateTime.parse("2022-11-19T00:00:00.147Z"),
                ZonedDateTime.parse("2023-11-23T00:00:00.147Z"),
                17L,
                "details",
                false,
                17L,
                new Subcategory(6L, "Chairs", new Category(4L, "Home")),
                new User(1L, "user1", "user1", "user1",
                        passwordEncoder.encode("user1"), "user1", "user1",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z"))));

        Image image12 = new Image(12L, "curtains.jpg", new Product(15L,
                "Curtains",
                ZonedDateTime.parse("2022-11-18T00:00:00.147Z"),
                ZonedDateTime.parse("2023-11-23T00:00:00.147Z"),
                12L,
                "details",
                false,
                12L,
                new Subcategory(6L, "Chairs", new Category(4L, "Home")),
                new User(1L, "user1", "user1", "user1",
                        passwordEncoder.encode("user1"), "user1", "user1",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z"))));

        Image image13 = new Image(13L, "carpet.jpg", new Product(16L,
                "Carpet",
                ZonedDateTime.parse("2022-11-19T00:00:00.147Z"),
                ZonedDateTime.parse("2023-11-23T00:00:00.147Z"),
                36L,
                "details",
                false,
                36L,
                new Subcategory(6L, "Chairs", new Category(4L, "Home")),
                new User(1L, "user1", "user1", "user1",
                        passwordEncoder.encode("user1"), "user1", "user1",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z"))));
        Image image14 = new Image(14L, "nightTable.jpg", new Product(17L,
                "Night table",
                ZonedDateTime.parse("2022-11-19T00:00:00.147Z"),
                ZonedDateTime.parse("2023-11-23T00:00:00.147Z"),
                16L,
                "details",
                false,
                16L,
                new Subcategory(6L, "Chairs", new Category(4L, "Home")),
                new User(1L, "user1", "user1", "user1",
                        passwordEncoder.encode("user1"), "user1", "user1",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z"))));

        Image image15 = new Image(15L, "pillow.jpg", new Product(18L,
                "Pillow",
                ZonedDateTime.parse("2022-11-18T00:00:00.147Z"),
                ZonedDateTime.parse("2023-11-23T00:00:00.147Z"),
                20L,
                "details",
                false,
                20L,
                new Subcategory(6L, "Chairs", new Category(4L, "Home")),
                new User(1L, "user1", "user1", "user1",
                        passwordEncoder.encode("user1"), "user1", "user1",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z"))));

        Image image16 = new Image(16L, "blanket.jpg", new Product(19L,
                "Blanket",
                ZonedDateTime.parse("2022-11-19T00:00:00.147Z"),
                ZonedDateTime.parse("2023-11-23T00:00:00.147Z"),
                7L,
                "details",
                false,
                7L,
                new Subcategory(6L, "Chairs", new Category(4L, "Home")),
                new User(1L, "user1", "user1", "user1",
                        passwordEncoder.encode("user1"), "user1", "user1",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z"))));

        Image image17 = new Image(17L, "shelf.jpg", new Product(20L,
                "Shelf",
                ZonedDateTime.parse("2022-11-19T00:00:00.147Z"),
                ZonedDateTime.parse("2023-11-23T00:00:00.147Z"),
                10L,
                "details",
                false,
                10L,
                new Subcategory(6L, "Chairs", new Category(4L, "Home")),
                new User(1L, "user1", "user1", "user1",
                        passwordEncoder.encode("user1"), "user1", "user1",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z"))));

        Image image18 = new Image(18L, "blueBag.jpg", new Product(1L,
                "Blue bag",
                ZonedDateTime.parse("2021-06-15T00:00:00.147Z"),
                ZonedDateTime.parse("2022-11-23T00:00:00.147Z"),
                15L,
                "details",
                false,
                15L,
                new Subcategory(1L, "Bags", new Category(2L, "Woman")),
                new User(2L, "user2", "user2", "user2",
                        passwordEncoder.encode("user2"), "user2", "user2",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z"))));

        Image image19 = new Image(19L, "patrick.jpg", new Product(2L,
                "Patrick",
                ZonedDateTime.parse("2021-05-12T00:00:00.147Z"),
                ZonedDateTime.parse("2022-11-22T00:00:00.147Z"),
                150L,
                "details",
                false,
                150L,
                new Subcategory(8L, "Toys", new Category(3L, "Kids")),
                new User(2L, "user2", "user2", "user2",
                        passwordEncoder.encode("user2"), "user2", "user2",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z"))));
        Image image20 = new Image(20L, "blackTable.jpg", new Product(9L,
                "Black table",
                ZonedDateTime.parse("2021-11-02T00:00:00.147Z"),
                ZonedDateTime.parse("2022-11-23T00:00:00.147Z"),
                14L,
                "details",
                false,
                14L,
                new Subcategory(7L, "Tables", new Category(4L, "Home")),
                new User(2L, "user2", "user2", "user2",
                        passwordEncoder.encode("user2"), "user2", "user2",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z"))));
        Image image21 = new Image(21L,"blueBag1.jpg",new Product(1L,
                "Blue bag",
                ZonedDateTime.parse("2021-06-15T00:00:00.147Z"),
                ZonedDateTime.parse("2022-11-23T00:00:00.147Z"),
                15L,
                "details",
                false,
                15L,
                new Subcategory(1L, "Bags", new Category(2L, "Woman")),
                new User(2L, "user2", "user2", "user2",
                        passwordEncoder.encode("user2"), "user2", "user2",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z"))));
        Image image22 = new Image(22L,"blueBag2.jpg",new Product(1L,
                "Blue bag",
                ZonedDateTime.parse("2021-06-15T00:00:00.147Z"),
                ZonedDateTime.parse("2022-11-23T00:00:00.147Z"),
                15L,
                "details",
                false,
                15L,
                new Subcategory(1L, "Bags", new Category(2L, "Woman")),
                new User(2L, "user2", "user2", "user2",
                        passwordEncoder.encode("user2"), "user2", "user2",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z"))));
        Image image23 = new Image (23L, "blueBag3.jpg",new Product(1L,
                "Blue bag",
                ZonedDateTime.parse("2021-06-15T00:00:00.147Z"),
                ZonedDateTime.parse("2022-11-23T00:00:00.147Z"),
                15L,
                "details",
                false,
                15L,
                new Subcategory(1L, "Bags", new Category(2L, "Woman")),
                new User(2L, "user2", "user2", "user2",
                        passwordEncoder.encode("user2"), "user2", "user2",
                        ZonedDateTime.parse("2022-04-12T00:00:00.147Z"))));
        listOfImages.add(image1);
        listOfImages.add(image2);
        listOfImages.add(image3);
        listOfImages.add(image4);
        listOfImages.add(image5);
        listOfImages.add(image6);
        listOfImages.add(image7);
        listOfImages.add(image8);
        listOfImages.add(image9);
        listOfImages.add(image10);
        listOfImages.add(image11);
        listOfImages.add(image12);
        listOfImages.add(image13);
        listOfImages.add(image14);
        listOfImages.add(image15);
        listOfImages.add(image16);
        listOfImages.add(image17);
        listOfImages.add(image18);
        listOfImages.add(image19);
        listOfImages.add(image20);
        listOfImages.add(image21);
        listOfImages.add(image22);
        listOfImages.add(image23);
        try {
            imageRepository.saveAll(listOfImages);
        } catch (Exception e) {
            initializeImage = "An error occurred while initializing the Image table ";
        }
    }

    /**
     * A method that checks whether each table has been filled with data and that no
     * error occurred when inserting data
     * @return Response object that contains status code and a message
     */

    @Override
    public Response checkIfAnErrorOccurred() {
        Response response = new Response();
        if (initializeCategory.length() != 0) {
            response.setStatusCode(400L);
            response.setMessage(initializeCategory);
            return response;
        }
        if (initializeSubcategory.length() != 0) {
            response.setStatusCode(400L);
            response.setMessage(initializeSubcategory);
            return response;
        }
        if (initializeUser.length() != 0) {
            response.setStatusCode(400L);
            response.setMessage(initializeUser);
            return response;
        }
        if (initializeProduct.length() != 0) {
            response.setStatusCode(400L);
            response.setMessage(initializeProduct);
            return response;
        }
        if (initializeImage.length() != 0) {
            response.setStatusCode(400L);
            response.setMessage(initializeImage);
            return response;
        }
        if (initializeBid.length() != 0) {
            response.setStatusCode(400L);
            response.setMessage(initializeBid);
            return response;
        }
        response.setStatusCode(200L);
        response.setMessage("Database has been initialized");
        return response;
    }
}
