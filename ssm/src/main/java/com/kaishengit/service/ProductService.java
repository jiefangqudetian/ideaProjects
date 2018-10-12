package com.kaishengit.service;

import com.github.pagehelper.PageInfo;
import com.kaishengit.entity.Product;
import com.kaishengit.entity.ProductType;

import java.util.List;
import java.util.Map;

public interface ProductService {
    Product findById(Integer id);



    /**
     * 分页查询商品列表
     * @date 2018/4/10
     * @param []
     * @param pageNo
     * @return com.github.pagehelper.PageInfo<com.kaishengit.entity.Product>
     */
    PageInfo<Product> findAllProductsByPageNo(Integer pageNo);
    /**
     *查询所有商品类型
     * @date 2018/4/10
     * @param []
     * @return java.util.List<com.kaishengit.entity.ProductType>
     */
    List<ProductType> findAllProductType();

    /**
     *保存商品
     * @date 2018/4/10
     * @param [product]
     * @return void
     */
    void saveProduct(Product product);

    /**
     *根据商品id删除商品
     * @date 2018/4/10
     * @param [id]
     * @return void
     */
    void delProductById(Integer id);

    /**
     *修改商品
     * @date 2018/4/10
     * @param [product]
     * @return void
     */
    void updateProduct(Product product);

    /**
     *根据页码和条件查询
     * @date 2018/4/11
     * @param [pageNo 页码, params 查询参数Map集合]
     * @return com.github.pagehelper.PageInfo<com.kaishengit.entity.Product>
     */
    PageInfo<Product> findAllProductsByPageNoAndQueryParam(Integer pageNo, Map<String, Object> queryParamMap);
}
