package com.developer.auctionapp.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * A class that contains tests for testing the methods of the InitializeServiceImpl class
 */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class InitializeServiceImplTest {

    @Mock
    private InitializeServiceImpl initializeServiceImpl;

    /**
     * A method that tests a class method that initializes the category table
     */

    @Test
    void initializeCategoryTable() {
        final ResponseEntity<Object> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        Mockito.when(initializeServiceImpl.initializeCategoryTable()).thenReturn(responseEntity);
        final ResponseEntity<Object> result = initializeServiceImpl.initializeCategoryTable();
        Assertions.assertEquals(200, result.getStatusCodeValue());
    }

    /**
     * A method that tests a class method that initializes the subcategory table
     */

    @Test
    void initializeSubcategoryTable() {
        final ResponseEntity<Object> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        Mockito.when(initializeServiceImpl.initializeSubcategoryTable()).thenReturn(responseEntity);
        final ResponseEntity<Object> result = initializeServiceImpl.initializeSubcategoryTable();
        Assertions.assertEquals(200, result.getStatusCodeValue());
    }

    /**
     * A method that tests a class method that initializes the role table
     */

    @Test
    void initializeRoleTable() {
        final ResponseEntity<Object> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        Mockito.when(initializeServiceImpl.initializeRoleTable()).thenReturn(responseEntity);
        final ResponseEntity<Object> result = initializeServiceImpl.initializeRoleTable();
        Assertions.assertEquals(200, result.getStatusCodeValue());
    }

    /**
     * A method that tests a class method that initializes the databse
     */

    @Test
    void initializeDatabase() {
        final ResponseEntity<Object> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        Mockito.when(initializeServiceImpl.initializeDatabase()).thenReturn(responseEntity);
        final ResponseEntity<Object> result = initializeServiceImpl.initializeDatabase();
        Assertions.assertEquals(200, result.getStatusCodeValue());
    }
}