package com.kaishengit.service;

import com.github.pagehelper.PageInfo;
import com.kaishengit.entity.Product;

public interface ProductService {

    void saveNewProduct(Product products);

    void deleteById(Integer id);

    void editProduct(Product products);

    void buyProduct(Integer id) throws RuntimeException;

    Product findById(Integer id);

    PageInfo<Product> findByPageNo(Integer pageNo);


}
