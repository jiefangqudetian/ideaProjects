package com.kaishengit.mapper;

import com.kaishengit.entity.Product;
import com.kaishengit.entity.ProductExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ProductMapper {
    long countByExample(ProductExample example);

    int deleteByExample(ProductExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    List<Product> selectByExample(ProductExample example);

    Product findById(Integer id);

    int updateByExampleSelective(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    List<Product> findAllWithType();

    void updateProduct(Product product);

    List<Product> findAllWithTypeAndParams(Map<String, Object> queryParamMap);
}