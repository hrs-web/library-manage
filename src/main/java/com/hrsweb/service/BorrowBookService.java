package com.hrsweb.service;

import com.hrsweb.pojo.BorrowBooks;
import com.hrsweb.pojo.PageResult;

public interface BorrowBookService {

    PageResult<BorrowBooks> queryByPage(Integer page, Integer rows, String sortBy, String mode);
}
