package com.hrsweb.mapper;

import com.hrsweb.pojo.Manger;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface MangerMapper extends Mapper<Manger> {
    @Select("select id,username,password from tb_manger where username = #{username} and password = #{password}")
    Manger login(String username, String password);
}
