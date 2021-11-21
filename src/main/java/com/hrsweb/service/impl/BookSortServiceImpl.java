package com.hrsweb.service.impl;

import com.hrsweb.mapper.BookSortMapper;
import com.hrsweb.pojo.BookSort;
import com.hrsweb.service.BookSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BookSortServiceImpl implements BookSortService {
    @Autowired
    private BookSortMapper bookSortMapper;

    /**
     * 查询所有分类
     * @return
     */
    @Override
    public List<BookSort> querySortByPage() {
        return bookSortMapper.selectAll();
    }

    @Override
    public void deleteById(Long id) {
        bookSortMapper.deleteByPrimaryKey(id);
    }

    // 查询分类名称
    @Override
    public List<BookSort> queryBySortName() {
        Example example = Example.builder(BookSort.class).select("id","sortName").build();
        return this.bookSortMapper.selectByExample(example);
    }

    // 添加分类
    @Override
    public void addSort(BookSort sort) {
        this.bookSortMapper.addSort(sort.getPid(),sort.getSortName(),sort.getDescription());
    }
}
