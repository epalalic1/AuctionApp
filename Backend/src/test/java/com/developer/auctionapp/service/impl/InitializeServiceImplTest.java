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

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class InitializeServiceImplTest {

    @Mock private InitializeServiceImpl initializeServiceImpl;

    @Test
    void initializeCategoryTable() {
        ResponseEntity<Object> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        Mockito.when(initializeServiceImpl.initializeCategoryTable()).thenReturn(responseEntity);
        ResponseEntity<Object> result = initializeServiceImpl.initializeCategoryTable();
        Assertions.assertEquals(200,result.getStatusCodeValue());
    }

    @Test
    void initializeSubcategoryTable() {
        ResponseEntity<Object> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        Mockito.when(initializeServiceImpl.initializeSubcategoryTable()).thenReturn(responseEntity);
        ResponseEntity<Object> result = initializeServiceImpl.initializeSubcategoryTable();
        Assertions.assertEquals(200,result.getStatusCodeValue());
    }

    @Test
    void initializeRoleTable() {
        ResponseEntity<Object> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        Mockito.when(initializeServiceImpl.initializeRoleTable()).thenReturn(responseEntity);
        ResponseEntity<Object> result = initializeServiceImpl.initializeRoleTable();
        Assertions.assertEquals(200,result.getStatusCodeValue());
    }

    @Test
    void initializeDatabase() {
        ResponseEntity<Object> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        Mockito.when(initializeServiceImpl.initializeDatabase()).thenReturn(responseEntity);
        ResponseEntity<Object> result = initializeServiceImpl.initializeDatabase();
        Assertions.assertEquals(200,result.getStatusCodeValue());
    }
}