package com.example.caveadventure.dao;

import com.example.caveadventure.entity.ProductEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
/*物品的持久层接口*/
public interface ProductMapper {
    /**
     * 根据物品id来查询物品数据
     * @param id 物品id
     * @return 如果找到则返回对象，反之返回null值
     */
    ProductEntity findById(Integer id);
}
