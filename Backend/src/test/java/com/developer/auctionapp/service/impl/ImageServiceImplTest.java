package com.developer.auctionapp.service.impl;

import com.developer.auctionapp.entity.Image;
import com.developer.auctionapp.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A class that contains tests for testing the methods of the ImageServiceImpl class
 */

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class ImageServiceImplTest {

    @Mock private ImageServiceImpl imageServiceImpl;

    /**
     * A method that tests a class method that returns all images
     */

    @Test
    void getAll() {
        final List<Image> list = new ArrayList<>();
        final Image image1 = new Image ("image1", new Product());
        final Image image2 = new Image ("image2", new Product());
        final Image image3 = new Image ("image3", new Product());
        final Image image4 = new Image ("image4", new Product());
        list.add(image1);
        list.add(image2);
        list.add(image3);
        list.add(image4);
        Mockito.when(imageServiceImpl.getAll()).thenReturn(ResponseEntity.of(Optional.of(list)));
        final List<Image> result = imageServiceImpl.getAll().getBody();
        Assertions.assertEquals(4, result.size());
        Assertions.assertEquals("image2", result.get(1).getName());
        Assertions.assertEquals("image4", result.get(3).getName());
    }
}