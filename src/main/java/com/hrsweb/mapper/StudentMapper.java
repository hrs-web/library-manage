package com.hrsweb.mapper;

import com.hrsweb.pojo.BorrowBooks;
import com.hrsweb.pojo.Student;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface StudentMapper extends Mapper<Student> {
    @Select("select * from borrow_books where sid = #{id} and status = 0")
    List<BorrowBooks> findBorrowBooksBySid(Long id);
}
