package com.kaishengit.entity;

public class ProductType {

    private int id;
    private String typeName;
    private int parentId;

    public ProductType() {
    }

    public ProductType(int id, String typeName, int parentId) {
        this.id = id;
        this.typeName = typeName;
        this.parentId = parentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "ProductType{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                ", parentId=" + parentId +
                '}';
    }
}
