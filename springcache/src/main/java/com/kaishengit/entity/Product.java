package com.kaishengit.entity;

import java.math.BigDecimal;

public class Product {
    private Integer id;
    private String productName;
    private BigDecimal price;
    private BigDecimal marketPlace;
    private String place;
    private Integer commentNum;
    private Integer typeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getMarketPlace() {
        return marketPlace;
    }

    public void setMarketPlace(BigDecimal marketPlace) {
        this.marketPlace = marketPlace;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", marketPlace=" + marketPlace +
                ", place='" + place + '\'' +
                ", commentNum=" + commentNum +
                ", typeId=" + typeId +
                '}';
    }
}
