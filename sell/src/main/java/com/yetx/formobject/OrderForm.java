package com.yetx.formobject;

import com.yetx.dto.CartDTO;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class OrderForm {
//    {
//        "name": "张三",
//            "phone": "18868822111",
//            "address": "慕课网总部",
//            "openid": "ew3euwhd7sjw9diwkq",
//            "items": [{
//        "productId": "1423113435324",
//                "productQuantity": 2
//    }]
//    }
    @NotEmpty(message = "姓名不能为空")
    private String name;
    @NotEmpty(message = "电话不能为空")
    private String phone;
    @NotEmpty(message = "地址不能为空")
    private String address;
    @NotEmpty(message = "微信openid不能为空")
    private String openid;

    @NotEmpty(message = "购物不能为空")
    private List<CartDTO> items;

    @Override
    public String toString() {
        return "OrderForm{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", openid='" + openid + '\'' +
                ", items='" + items + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public List<CartDTO> getItems() {
        return items;
    }

    public void setItems(List<CartDTO> items) {
        this.items = items;
    }
}
