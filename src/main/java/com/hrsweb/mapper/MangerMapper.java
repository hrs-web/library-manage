package com.hrsweb.mapper;

import com.hrsweb.pojo.Manger;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

public interface MangerMapper extends Mapper<Manger> {
    @Select("select id,username,password from tb_manger where username = #{username} and password = #{password}")
    Manger login(String username, String password);

    @Select("select * from tb_manger where username = #{username} and password = #{oldPassword}")
    Manger queryByManger(String username, String oldPassword);

    @Update("update tb_manger set password = #{newPassword} where id = #{id}")
    void password(Long id,String newPassword);
}
