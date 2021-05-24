package com.yc.mapper.second;

import com.yc.entity.Address;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author yucheng
 * @Date 2021/3/7 19:32
 */
@Repository
public interface AddressMapper {

    int insertWithFields(Address address);

    List<Address> findByCityName(String cityName);

    int delete(int id);

}
