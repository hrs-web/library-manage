package com.hrsweb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hrsweb.mapper.BorrowBookMapper;
import com.hrsweb.pojo.BorrowBooks;
import com.hrsweb.pojo.PageResult;
import com.hrsweb.service.BorrowBookService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BorrowBookServiceImpl implements BorrowBookService {
    @Autowired
    private BorrowBookMapper borrowBookMapper;

    /**
     * 组合查询
     * @param page
     * @param rows
     * @param sortBy
     * @param mode
     * @return
     */
    @Override
    public PageResult<BorrowBooks> queryByPage(Integer page, Integer rows, String sortBy, String mode) {
        // 1.初始化Example对象
        Example example = new Example(BorrowBooks.class);
        // 2.添加分页
        PageHelper.startPage(page,rows);
        // 3.添加排序
        if (StringUtils.isNotBlank(sortBy)){
            example.setOrderByClause((sortBy.equals("borrowDate"))?"borrow_date":sortBy + " " + mode);
        }
        // 4.调用mapper
        List<BorrowBooks> borrowBooks = this.borrowBookMapper.selectByExample(example);

        // 5.完善其他字段
        borrowBooks.forEach(borrowBook->{
            Long sid = borrowBook.getSid();
            Long bid = borrowBook.getBid();
            Long mid = borrowBook.getMid();
            String studentName = this.borrowBookMapper.selectStuName(sid);
            String bookName = this.borrowBookMapper.selectBookName(bid);
            String mangerName = this.borrowBookMapper.selectMangeName(mid);
            borrowBook.setStudentName(studentName);
            borrowBook.setBookName(bookName);
            borrowBook.setMangerName(mangerName);
        });
        PageInfo<BorrowBooks> info = new PageInfo<>(borrowBooks);
        return new PageResult<>(info.getTotal(),info.getList());
    }
}
