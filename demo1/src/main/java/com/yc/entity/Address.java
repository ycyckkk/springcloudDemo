package com.yc.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author yucheng
 * @Date 2021/3/7 18:40
 */
@ApiModel(description = "地址实体类")
public class Address {

    @ApiModelProperty(value = "地址ID")
    private Integer id;

    @ApiModelProperty(value = "城市名", required = true)
    private String city;

    @ApiModelProperty(value = "街道名", required = true)
    private String street;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
