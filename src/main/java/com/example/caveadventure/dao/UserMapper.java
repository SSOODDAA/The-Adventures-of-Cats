package com.example.caveadventure.dao;


import com.example.caveadventure.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
/*用户模块的持久层接口*/
public interface UserMapper {
    /**
     * 插入用户的数据
     * @param user 用户的数据
     * @return 受影响的行数（增删改查影响的行数作为返回值，根据返回值判断是否成功）
     */
    Integer insert(UserEntity user);/*用户类*/

    /**
     * 根据用户名来查询用户的数据
     * @param username 用户名
     * @return 如果找到对应的用户则返回这个用户的数据，如果没有找到则返回null值
     */
    UserEntity findByUsername(String username);

    /**
     * 根据用户userid来修改用户姓名
     * @param userid 用户id
     * @param username 用户输入的新名称
     * @return 受影响的行数
     */
    Integer updateUsernameByUserid(Integer userid, String username);

    /**
     *根据用户的userid来修改用户密码
     * @param userid 用户id
     * @param pwd 用户输入的新密码
     * @return 返回值为受影响的行数
     */
    Integer updatePasswordByUserid(Integer userid, String pwd);

    /**
     * 根据用户的id查询用户的数据
     * @param userid 用户的id
     * @return 如果找到则返回对象，反之返回null值
     */
    UserEntity findByUserid(Integer userid);
}