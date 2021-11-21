package com.hrsweb.mapper;

import com.hrsweb.pojo.BookSort;
import org.apache.ibatis.annotations.Insert;
import tk.mybatis.mapper.common.Mapper;

public interface BookSortMapper extends Mapper<BookSort> {
    @Insert("INSERT INTO book_sort(pid,sort_name,description) VALUES(#{pid},#{sortName},#{description})")
    void addSort(Long pid, String sortName, String description);
}
