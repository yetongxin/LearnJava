package com.yetx.dto;

public class CartDTO {

    private String ProductId;

    private Integer ProductQuantity;

    public CartDTO(){
    }

    public CartDTO(String productId,Integer productQuantity){
        this.ProductId = productId;
        this.ProductQuantity = productQuantity;
    }
    public Integer getProductQuantity() {
        return ProductQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        ProductQuantity = productQuantity;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }
}
