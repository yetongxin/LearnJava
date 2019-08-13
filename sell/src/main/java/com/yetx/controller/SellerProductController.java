package com.yetx.controller;

import com.yetx.daoobject.ProductCategory;
import com.yetx.daoobject.ProductInfo;
import com.yetx.enums.SellResultEnums;
import com.yetx.exception.SellException;
import com.yetx.formobject.ProductForm;
import com.yetx.service.ProductCategoryService;
import com.yetx.service.ProductInfoService;
import com.yetx.utils.RandomIdUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private ProductCategoryService productCategoryService;
    @GetMapping("/list")
    public ModelAndView showAllProduct(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                       @RequestParam(value = "size",defaultValue = "5") Integer size,
                                       Map<String,Object> map){
        PageRequest pageRequest = PageRequest.of(page-1,size);
        Page<ProductInfo> productInfoPage = productInfoService.findAll(pageRequest);
        map.put("productInfoPage",productInfoPage);
        map.put("page",page);
        map.put("size",size);
        //System.out.println("page:{}"+page+"  "+map.get("page"));
        return new ModelAndView("product/list",map);
    }
    @GetMapping("/upload")
    public ModelAndView uploadProduct(@RequestParam(value = "productId") String productId,
                                      Map<String,Object> map){
        try {
            productInfoService.upProduct(productId);
        }
        catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg",SellResultEnums.PRODUCT_UPLOAD_SUCCESS.getMsg());
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);

    }
    @GetMapping("/download")
    public ModelAndView downloadProduct(@RequestParam(value = "productId") String productId,
                                        Map<String,Object> map){
        try {
            productInfoService.downProduct(productId);
        }
        catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg", SellResultEnums.PRODUCT_DOWNLOAD_SUCCESS.getMsg());
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);

    }
    @GetMapping("/add")
    public ModelAndView addProduct(@RequestParam(value = "productId",required = false,defaultValue = "") String productId,
                                   Map<String,Object> map){
        if(!productId.equals("")){
            map.put("product",productInfoService.findById(productId));
        }
        else {
            map.put("product",null);
        }
        List<ProductCategory> categoryList = productCategoryService.findAll();
        map.put("categoryList",categoryList);
        return new ModelAndView("product/add",map);
    }
    @PostMapping("/addsubmit")
    public ModelAndView addSubmitCheck(@Valid ProductForm productForm,
                                       BindingResult bindingResult,
                                       Map<String,Object> map){
        if(bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError());
            map.put("url","/sell/seller/product/add");
            return new ModelAndView("common/error",map);
        }
        ProductInfo productInfo = new ProductInfo();
        //从数据库中找出该id的product，并将属性拷贝到其上
        if(StringUtils.isEmpty(productForm.getProductId())){//新增
            String productId = RandomIdUtils.getRandomId();
            productForm.setProductId(productId);
        }
        else{
            try {
                productInfo = productInfoService.findById(productForm.getProductId());
            }
            catch (SellException e){
                map.put("msg",e.getMessage());
                map.put("url","/sell/seller/product/add");
                return new ModelAndView("common/error",map);
            }
        }
        BeanUtils.copyProperties(productForm,productInfo);
        productInfoService.save(productInfo);
        map.put("msg",SellResultEnums.PRODUCT_CHANGE_SUCCESS.getMsg());
        map.put("url","/sell/seller/product/add");
        return new ModelAndView("common/success",map);
    }
}
