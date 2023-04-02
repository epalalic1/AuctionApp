package com.developer.auctionapp.service.impl;

import com.developer.auctionapp.dto.response.CategoryResponse;
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
 * A class that contains tests for testing the methods of the CategoryServiceImpl class
 */

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class CategoryServiceImplTest {

    @Mock
    private CategoryServiceImpl categoryServiceImpl;

    /**
     * A method that tests a class method that returns all categories
     */

    @Test
    void getAll() {
        final List<CategoryResponse> list = new ArrayList<>();
        final CategoryResponse firstCategory = new CategoryResponse(
                1L,
                "firstCategory",
                new ArrayList<>(),
                false);
        final CategoryResponse secondCategory = new CategoryResponse(
                2L,
                "secondCategory",
                new ArrayList<>(),
                true);
        final CategoryResponse thirdCategory = new CategoryResponse(
                3L,
                "thirdCategory",
                new ArrayList<>(),
                false);
        list.add(firstCategory);
        list.add(secondCategory);
        list.add(thirdCategory);
        Mockito.when(categoryServiceImpl.getAll()).thenReturn(ResponseEntity.of(Optional.of(list)));
        final List<CategoryResponse> result = categoryServiceImpl.getAll().getBody();
        assert result != null;
        Assertions.assertEquals(3, result.size());
        Assertions.assertEquals("firstCategory", result.get(0).getName());
        Assertions.assertTrue(result.get(1).isChecked());

    }
}