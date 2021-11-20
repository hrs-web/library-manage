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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // 查询学生姓名，书籍名称，管理员姓名
    @Override
    public Map<String, String> findBook(Long sid, Long bid) {
        String studentName = this.borrowBookMapper.selectStuName(sid);
        String bookName = this.borrowBookMapper.selectBookName(bid);

        HashMap<String, String> map = new HashMap<>();
        map.put("studentName",studentName);
        map.put("bookName",bookName);

        return map;
    }

    // 借阅书籍
    @Override
    public void insertBorrow(BorrowBooks borrowBooks) {
        this.borrowBookMapper.insertSelective(borrowBooks);
    }

    // 查询待还书籍记录
    @Override
    public PageResult<BorrowBooks> queryByReturnPage(String studentName, String bookName, Integer page, Integer rows, String sortBy, String mode) {
        // 定义sql
        String sql = "SELECT bb.id,s.student_id AS studentId,bb.status,s.name AS studentName,s.phone AS phone,b.book_name AS bookName,bb.borrow_date borrowDate,bb.end_date endDate from borrow_books bb LEFT JOIN tb_student s ON bb.sid=s.id LEFT JOIN tb_books b ON bb.bid=b.id WHERE bb.status = 0 ";
        StringBuilder sb = new StringBuilder(sql);
        if (StringUtils.isNotBlank(studentName)){
            sb.append(" And name LIKE '%"+studentName+"%' ");
        }
        if (StringUtils.isNotBlank(bookName)){
            sb.append(" AND book_name LIKE '%"+bookName+"%' ");
        }
        if (StringUtils.isNotBlank(sortBy)){
            String str = sortBy.equals("studentId") ? "student_id" : "borrow_date";
            sb.append(" ORDER BY "+str+" "+mode);
        }
        sql = sb.toString();
        //System.out.println(sql);
        List<BorrowBooks> borrowBooks = this.borrowBookMapper.queryReturnPage(sql);

        // 分页
        PageHelper.startPage(page,rows);
        PageInfo<BorrowBooks> info = new PageInfo<>(borrowBooks);
        return new PageResult<>(info.getTotal(),info.getList());
    }

    @Override
    public void alterStatus(Long id) {
        this.borrowBookMapper.alertStatus(id);
    }
}
