package com.kaishengit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.kaishengit.entity.Product;
import com.kaishengit.entity.ProductType;
import com.kaishengit.mapper.ProductMapper;
import com.kaishengit.mapper.ProductTypeMapper;
import com.kaishengit.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService{


    private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductTypeMapper productTypeMapper;
    /*@Autowired
    private JedisPool jedisPool;*/

    @Autowired
    private CacheManager cacheManager;

    @PostConstruct
    public void initCache(){
        Cache cache = cacheManager.getCache("products");
        Product product = productMapper.findById(30910);
        cache.put(30910,product);
        logger.info("init {} product in cache",30910);
    }

    @Override
    @Cacheable("products")
    public Product findById(Integer id) {
        /*final String KEY = "product:" + id;
        Product product = null;

        Jedis jedis = jedisPool.getResource();
        //1.查询redis中是否存在该键
        if (jedis.exists(KEY)){
            String json = jedis.get(KEY);
            product = new Gson().fromJson(json,Product.class);
            logger.info("find Product {} from Redis",id);
        } else {
            product = productMapper.findById(id);
            if (product!=null){
                String json = new Gson().toJson(product);
                jedis.set(KEY,json);
            }
            logger.info("find Product {} from MySQL",id);
        }
        jedis.close();
        return product;*/


        logger.info("find Product {} from MySQL",id);

        return productMapper.findById(id);
    }

    /**
     * 分页查询商品列表
     *
     * @return com.github.pagehelper.PageInfo<com.kaishengit.entity.Product>
     * @date 2018/4/10
     * @param pageNo
     */
    @Override
    public PageInfo<Product> findAllProductsByPageNo(Integer pageNo) {
        PageHelper.startPage(pageNo,20);
        List<Product> productList = productMapper.findAllWithType();
        return new PageInfo<>(productList);
    }

    /**
     * 查询所有商品类型
     *
     * @return java.util.List<com.kaishengit.entity.ProductType>
     * @date 2018/4/10
     */
    @Override
    public List<ProductType> findAllProductType() {
        return productTypeMapper.findAllProductType();
    }

    /**
     * 保存商品
     *
     * @param product
     * @return void
     * @date 2018/4/10
     */
    @Override
    public void saveProduct(Product product) {

        product.setCommentNum(Product.DEFAULT_COMMENT_NUM);
        productMapper.insert(product);
        logger.info("保存商品{}",product);
    }

    /**
     * 根据商品id删除商品
     *
     * @param id
     * @return void
     * @date 2018/4/10
     */
    @Override
    @CacheEvict("products")
    public void delProductById(Integer id) {
        Product product = productMapper.findById(id);
        if (product != null){
            productMapper.deleteByPrimaryKey(id);
            logger.info("删除商品{}",product);
        }
    }

    /**
     * 修改商品
     * @param product
     * @return void
     * @date 2018/4/10
     */
    @Override
    @CacheEvict(key = "#product.id",value = "products")
    public void updateProduct(Product product) {
        productMapper.updateProduct(product);
        logger.info("修改商品{}",product);
    }

    /**
     *根据页码和条件查询
     * @date 2018/4/11
     * @param pageNo 页码, params 查询参数Map集合]
     * @return com.github.pagehelper.PageInfo<com.kaishengit.entity.Product>
     */
    @Override
    public PageInfo<Product> findAllProductsByPageNoAndQueryParam(Integer pageNo, Map<String, Object> queryParamMap) {

        PageHelper.startPage(pageNo,10);

        List<Product> productList = productMapper.findAllWithTypeAndParams(queryParamMap);
        return new PageInfo<>(productList);
    }


}
