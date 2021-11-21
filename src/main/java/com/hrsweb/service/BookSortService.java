package com.hrsweb.service;

import com.hrsweb.pojo.BookSort;

import java.util.List;

public interface BookSortService {
    List<BookSort> querySortByPage();

    void deleteById(Long id);

    List<BookSort> queryBySortName();

    void addSort(BookSort sort);
}
