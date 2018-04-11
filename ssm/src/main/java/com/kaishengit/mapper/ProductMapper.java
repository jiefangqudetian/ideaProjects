package com.kaishengit.mapper;

import com.kaishengit.entity.Product;

import java.util.List;

public interface ProductMapper {

    Product findById(Integer id);


    List<Product> findAllWithType();


    void saveProduct(Product product);

    void delProductById(Integer id);

    void updateProduct(Product product);
}
