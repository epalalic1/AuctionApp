package com.developer.auctionapp.service.impl;

import com.developer.auctionapp.dto.response.SubcategoryResponse;
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
 * A class that contains tests for testing the methods of the SubcategoryServiceImpl class
 */

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class SubcategoryServiceImplTest {

    @Mock
    private SubcategoryServiceImpl subcategoryServiceImpl;

    /**
     * A method that tests a class method that returns all subcategories
     */

    @Test
    void getAllSubcategories() {
        final List<SubcategoryResponse> list = new ArrayList<>();
        final SubcategoryResponse subcategoryResponse1 = new SubcategoryResponse(1L, "subcategory1", 1L);
        final SubcategoryResponse subcategoryResponse2 = new SubcategoryResponse(2L, "subcategory2", 2L);
        final SubcategoryResponse subcategoryResponse3 = new SubcategoryResponse(3L, "subcategory3", 3L);
        list.add(subcategoryResponse1);
        list.add(subcategoryResponse2);
        list.add(subcategoryResponse3);
        Mockito.when(subcategoryServiceImpl.getAllSubcategories()).thenReturn(ResponseEntity.of(Optional.of(list)));
        final List<SubcategoryResponse> result = subcategoryServiceImpl.getAllSubcategories().getBody();
        Assertions.assertEquals(3, result.size());
        Assertions.assertEquals("subcategory2", result.get(1).getName());
        Assertions.assertEquals(3L, result.get(2).getCategory());
    }
}