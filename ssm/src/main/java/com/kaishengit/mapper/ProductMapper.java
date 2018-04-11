package com.kaishengit.mapper;

import com.kaishengit.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProductMapper {

    Product findById(Integer id);


    List<Product> findAllWithType();


    void saveProduct(Product product);

    void delProductById(Integer id);

    void updateProduct(Product product);

    List<Product> findAllWithTypeAndParams( Map<String, Object> queryParamMap);
}
