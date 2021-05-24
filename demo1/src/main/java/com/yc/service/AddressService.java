package com.yc.service;

import com.google.common.collect.Maps;
import com.yc.entity.Address;
import com.yc.mapper.second.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author yucheng
 * @Date 2021/3/7 19:33
 */
@Service
public class AddressService {

    @Autowired
    AddressMapper addressMapper;

    public Address insertWithFields(Address address) {
        addressMapper.insertWithFields(address);
        return address;
    }

    public List<Address> findByCityName(String cityName) {
        return addressMapper.findByCityName(cityName);
    }

    public int delete(int id) {
        return addressMapper.delete(id);
    }

}
