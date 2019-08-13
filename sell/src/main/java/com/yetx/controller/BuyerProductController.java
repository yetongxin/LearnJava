package com.yetx.controller;

import com.yetx.daoobject.ProductCategory;
import com.yetx.daoobject.ProductInfo;
import com.yetx.service.impl.ProductCategoryServiceImpl;
import com.yetx.service.impl.ProductInfoServiceImpl;
import com.yetx.utils.ResultUtils;
import com.yetx.viewobject.ProductInfoVO;
import com.yetx.viewobject.ProductVO;
import com.yetx.viewobject.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoServiceImpl productService;

    @Autowired
    private ProductCategoryServiceImpl categoryService;
    @RequestMapping("/list")
    public ResultVO listAll(){
        List<ProductCategory> allcategory = categoryService.findAll();//所有类别
        List<ProductInfo> allproduct = productService.findUpALL();//只呈现上架的商品

        List<ProductVO> data = new ArrayList<>();
        for(ProductCategory category:allcategory){
            //每个类别下面加入一个ProductVO
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(category.getCategoryName());//name
            productVO.setCategoryType(category.getCategoryType());//type

            List<ProductInfoVO> foodslist = new ArrayList<>();
            for(ProductInfo product:allproduct){
                if(product.getCategoryType().equals(category.getCategoryType())){
                    ProductInfoVO p = new ProductInfoVO();
                    p.setProductId(product.getProductId());
                    p.setProductDescription(product.getProductDescription());
                    p.setProductIcon(product.getProductIcon());
                    p.setProductName(product.getProductName());
                    p.setProductPrice(product.getProductPrice());
//                    BeanUtils.copyProperties(product,p);
                    foodslist.add(p);
                }
            }
            productVO.setProductInfoVO(foodslist);//foods;
            data.add(productVO);
        }

        return ResultUtils.success(data);
    }
}
