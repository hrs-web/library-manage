package com.hrsweb.mapper;

import com.hrsweb.pojo.Book;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;


public interface BookMapper extends Mapper<Book> {
    @Select("select sort_name from book_sort where id = #{sortId}")
    String queryBookSortByName(Long sortId);
}
