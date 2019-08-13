package com.yetx.viewobject;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ProductVO {

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVO;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
    }

    public List<ProductInfoVO> getProductInfoVO() {
        return productInfoVO;
    }

    public void setProductInfoVO(List<ProductInfoVO> productInfoVO) {
        this.productInfoVO = productInfoVO;
    }
}
