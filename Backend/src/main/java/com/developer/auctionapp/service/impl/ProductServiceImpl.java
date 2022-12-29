package com.developer.auctionapp.service.impl;

import com.developer.auctionapp.dto.request.AddItemRequest;
import com.developer.auctionapp.dto.response.BiddersForProduct;
import com.developer.auctionapp.dto.response.ProductResponse;
import com.developer.auctionapp.dto.response.Response;
import com.developer.auctionapp.entity.Bid;
import com.developer.auctionapp.entity.Image;
import com.developer.auctionapp.entity.Product;
import com.developer.auctionapp.entity.Subcategory;
import com.developer.auctionapp.repository.*;
import com.developer.auctionapp.service.ProductService;
import com.developer.auctionapp.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Class that implements ProductService interface and we use it to comunicate with the database</p>
 */

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ImageRepository imageRepository;

    private final SubcategoryRepository subcategoryRepository;

    private final UserService userService;

    private final BidRepository bidRepository;

    public ProductServiceImpl(
            final ProductRepository productRepository,
            final ImageRepository imageRepository,
            final SubcategoryRepository subcategoryRepository,
            final UserService userService,
            final BidRepository bidRepository,
            final UserRepository userRepository
    ) {
        this.productRepository = productRepository;
        this.imageRepository = imageRepository;
        this.subcategoryRepository = subcategoryRepository;
        this.userService = userService;
        this.bidRepository = bidRepository;
    }

    /**
     * The method used to get all products from database and transform them into Data Transform Objects
     *
     * @return list of Data Transform Objects which each of them represent one Product
     */

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> listOfProducts = productRepository.findAll();
        List<ProductResponse> list = new ArrayList<>();
        for (Product res : listOfProducts) {
            List<Image> images = imageRepository.findByProduct(res);
            List<String> imageNames = images.stream().map(Image::getName).collect(Collectors.toList());
            final ProductResponse item = new ProductResponse(
                    res.getId(),
                    res.getName(),
                    res.getDateOfArriving(),
                    res.getEndDate(),
                    res.getStartPrice(),
                    res.getDetails(),
                    res.getStatus(),
                    res.getPrice(),
                    res.getSubcategory().getId(),
                    res.getUser().getId(),
                    imageNames,
                    res.getSubcategory().getCategory().getId());
            list.add(item);
        }
        return list;
    }

    /**
     * The method used to get all products whose arrival date is not older than 7 days compared to today's date
     *
     * @return list of Data Transform Objects which each of them represent one Product
     */

    @Override
    public List<ProductResponse> getNewProducts() {
        List<Product> listOfProducts = productRepository.findByDateOfArrivingAfter(ZonedDateTime.now().minusDays(7));
        List<ProductResponse> list = new ArrayList<>();
        for (Product res : listOfProducts) {
            List<Image> images = imageRepository.findByProduct(res);
            List<String> imageNames = images.stream().map(Image::getName).collect(Collectors.toList());
            final ProductResponse item = new ProductResponse(
                    res.getId(),
                    res.getName(),
                    res.getDateOfArriving(),
                    res.getEndDate(),
                    res.getStartPrice(),
                    res.getDetails(),
                    res.getStatus(),
                    res.getPrice(),
                    res.getSubcategory().getId(),
                    res.getUser().getId(),
                    imageNames,
                    res.getSubcategory().getCategory().getId());
            list.add(item);
        }
        return list;
    }

    /**
     * The method used to get all products whose end date lasts another seven days compared to today's date
     *
     * @return list of Data Transform Objects which each of them represent one Product
     */

    @Override
    public List<ProductResponse> getLastChanceProducts() {
        List<Product> listOfProducts = productRepository.findByEndDateBefore(ZonedDateTime.now().plusDays(7));
        List<ProductResponse> list = new ArrayList<>();
        for (Product res : listOfProducts) {
            List<Image> images = imageRepository.findByProduct(res);
            List<String> imageNames = images.stream().map(Image::getName).collect(Collectors.toList());
            final ProductResponse item = new ProductResponse(
                    res.getId(),
                    res.getName(),
                    res.getDateOfArriving(),
                    res.getEndDate(),
                    res.getStartPrice(),
                    res.getDetails(),
                    res.getStatus(),
                    res.getPrice(),
                    res.getSubcategory().getId(),
                    res.getUser().getId(),
                    imageNames,
                    res.getSubcategory().getCategory().getId());
            list.add(item);
        }
        return list;
    }

    /**
     * The method that adds a single product to the database along with the corresponding
     * image for that product
<<<<<<< HEAD
=======
     *
>>>>>>> 7d10722a (Changes)
     * @param addItemRequest DTO object with information of product
     * @return reponse object
     */
    @Override
    public Response addProduct(final AddItemRequest addItemRequest) {
        LocalDate start = addItemRequest.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate end = addItemRequest.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDate startDate = LocalDate.parse(start.toString(), formatter);
        ZonedDateTime zdtStart = startDate.atStartOfDay().atZone(ZoneId.of("Europe/Amsterdam"));
        LocalDate endDate = LocalDate.parse(end.toString(), formatter);
        ZonedDateTime zdtEnd = endDate.atStartOfDay().atZone(ZoneId.of("Europe/Amsterdam"));
        Subcategory subcategory = subcategoryRepository.findByName(addItemRequest.getSubcategory());
        Product product = new Product(
                addItemRequest.getName(),
                zdtStart,
                zdtEnd,
                (long) addItemRequest.getStartPrice(),
                addItemRequest.getDescription(),
                false,
                0L,
                subcategory,
                userService.getCurrentUser()
        );
        productRepository.save(product);
        Image image = new Image(
                imageRepository.getMaxId() + 1,
                addItemRequest.getImageName(),
                product
        );
        imageRepository.save(image);
        return new Response(200l, "New product successfully added");
    }

    /**
     * The method that returns all product bidders
     * @param id  based on which we want to find the product bidders
     * @return list of all bidders
     */

    @Override
    public List<BiddersForProduct> findBiddersForProduct(final Long id) {
        final List<Bid> bids = bidRepository.findAll();
        List<BiddersForProduct> list = new ArrayList<>();
        for (Bid bid : bids) {
            if (bid.getProduct().getId() == id){
                BiddersForProduct biddersForProduct = new BiddersForProduct(
                       bid.getUser().getName(),
                        Date.from(bid.getDateOfBid().toInstant()),
                        bid.getAmount()
                );
                list.add(biddersForProduct);
            }
        }
        return list;
    }

    /**
     * The method that returns product based on id
     * @param id based on which we want to find the product
     * @return ProductResponse object that contains all data about product
     */

    @Override
    public ProductResponse getProductFromId(final long id) {
        final Product product  = productRepository.findById(id).get();
        final List<Image> images = imageRepository.findByProduct(product);
        final List<String> imageNames = images.stream().map(Image::getName).collect(Collectors.toList());
        ProductResponse productResponse = new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDateOfArriving(),
                product.getEndDate(),
                product.getStartPrice(),
                product.getDetails(),
                product.getStatus(),
                product.getPrice(),
                product.getSubcategory().getId(),
                product.getUser().getId(),
                imageNames,
                product.getCategory().getId()
        );
        return productResponse;
    }
}
