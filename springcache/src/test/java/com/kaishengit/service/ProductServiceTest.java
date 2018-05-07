package com.kaishengit.service;

import com.kaishengit.Application;
import com.kaishengit.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class ProductServiceTest {

    @Autowired
    private ProductService productService;
    @Test
    public void findById() {
        Product product = productService.findById(2177);
        System.out.println(product);
    }
}