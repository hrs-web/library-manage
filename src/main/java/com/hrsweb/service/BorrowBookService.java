package com.hrsweb.service;

import com.hrsweb.pojo.BorrowBooks;
import com.hrsweb.pojo.PageResult;

import java.util.Map;

public interface BorrowBookService {

    PageResult<BorrowBooks> queryByPage(Integer page, Integer rows, String sortBy, String mode);

    Map<String,String> findBook(Long sid, Long bid);

    void insertBorrow(BorrowBooks borrowBooks);

    PageResult<BorrowBooks> queryByReturnPage(String studentName, String bookName, Integer page, Integer rows, String sortBy, String mode);

    void alterStatus(Long id);
}
