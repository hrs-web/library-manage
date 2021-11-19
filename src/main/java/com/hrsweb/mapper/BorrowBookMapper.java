package com.hrsweb.mapper;

import com.hrsweb.pojo.BorrowBooks;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface BorrowBookMapper extends Mapper<BorrowBooks> {
    @Select("select name from tb_student where id = #{sid}")
    String selectStuName(Long sid);

    @Select("select book_name from tb_books where id = #{bid}")
    String selectBookName(Long bid);

    @Select("select manger_name from tb_manger where id = #{mid}")
    String selectMangeName(Long mid);
}
