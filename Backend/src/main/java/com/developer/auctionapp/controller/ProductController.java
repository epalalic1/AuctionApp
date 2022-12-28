package com.developer.auctionapp.controller;

import com.developer.auctionapp.dto.request.AddItemRequest;
import com.developer.auctionapp.dto.response.ProductResponse;
import com.developer.auctionapp.dto.response.Response;
import com.developer.auctionapp.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * <p>Product controller</p>
 *
 * The rest controller with REST API calls to manipulate with Product objects on a route "/auctionapp/image"
 */

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/auctionapp/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * <p>A method that is triggered on a route "/auctionapp/product/getAll"</p>
     * @return all products from the database
     */

    @GetMapping("/getAll")
    public ResponseEntity<List<ProductResponse>> findAllProducts() {
        return productService.getAllProducts();
    }

    /**
     * <p>A method that is triggered on a route "/auctionapp/product/getNewProducts"</p>
     * @return all products with an arrival date that is not older than seven days
     */

    @GetMapping("/getNewProducts")
    public ResponseEntity<List<ProductResponse>>  getNewProducts() {
        return productService.getNewProducts();
    }

    /**
     * <p>A method that is triggered on a route "/auctionapp/product/getLastChanceProducts"</p>
     * @return all products with an end date  for bidding that will expire in less than seven days
     */

    @GetMapping("/getLastChanceProducts")
    public ResponseEntity<List<ProductResponse>> getLastChanceProducts() {
        return productService.getLastChanceProducts();
    }

    /**
     * <p>A method that is triggered on a route "/auctionapp/product/addItemRequest"</p>
     * @param addItemRequest DTO object that contains all data we need to add one product in database
     * @return response object that carries information about whether the object was successfully
     * added to the database
     */

    @PostMapping("/addItemRequest")
    public Response addItem (@RequestBody AddItemRequest addItemRequest){
        return productService.addProduct(addItemRequest);
    }

    /**
     *  <p>A method that is triggered on a route "/auctionapp/product/getBiddersForProduct"</p>
     * @param id of the product whose bidders we want to find
     * @return list of bidders
     */
    @GetMapping("/getBiddersForProduct")
    @ResponseBody
    public ResponseEntity<List<BiddersForProduct>>  getBiddersForProduct(@RequestParam(name = "paramName") long id) {
        return productService.findBiddersForProduct(id);
    }

    /**
     * <p>A method that is triggered on a route "/auctionapp/product/getProductFromId"</p>
     * @param id based on which we want to find the product
     * @return ProductResponse object that contains all data about product
     */

    @GetMapping("/getProductFromId")
    @ResponseBody
    public ProductResponse  getProduct(@RequestParam(name = "id") long id) {
        return productService.getProductFromId(id);
    }
}
