package com.hrsweb.service;

import com.hrsweb.pojo.Book;
import com.hrsweb.pojo.PageResult;

import java.util.List;

public interface BookService {
    PageResult<Book> queryByBookPage(String bookId, String bookName, Integer page, Integer rows, String sortBy, String mode);

    void insertBook(Book book);

    void deleteBooks(List<Long> ids);

    List<Book> findByIdName();
}
