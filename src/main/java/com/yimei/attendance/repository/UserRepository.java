package com.yimei.attendance.repository;

import com.yimei.attendance.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xiangyang on 15/11/5.
 */
public interface UserRepository {
    public void save(User user);

    public int update(User user);

    public int updateByIdSelective(User user);

    @Select("select  id, loginname, name, password, salt, email, activated from  user where id=#{value}")
    @ResultMap("BaseResultMap")
    public User findById(long id);


    @Select("select * from user where loginname = #{value}")
    @ResultMap("BaseResultMap")
    public User findByLoginname(String username);

    @Select("select * from user where email = #{value}")
    @ResultMap("BaseResultMap")
    public User findByEmail(String email);

    public User findUserAndRoleById(long id);

    public int findAllCount(@Param("loginname") String loginname);

//    public List<User> findAll(@Param("page") PageParam pageParam, @Param("loginname") String loginname);
}
