package com.example.caveadventure.dao;

import com.example.caveadventure.entity.RoleEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
/*角色模块的持久层接口*/
public interface RoleMapper {
    /**
     * 根据角色id来查询角色数据
     * @param id 角色的id
     * @return 如果找到则返回对象，反之返回null值
     */
    RoleEntity findByRoleid(Integer id);
}
