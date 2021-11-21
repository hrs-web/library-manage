package com.hrsweb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hrsweb.mapper.BookMapper;
import com.hrsweb.pojo.Book;
import com.hrsweb.pojo.PageResult;
import com.hrsweb.service.BookService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    /**
     * 组合查询书籍
     * @param bookId
     * @param bookName
     * @param page
     * @param rows
     * @param sortBy
     * @param mode
     * @return
     */
    @Override
    public PageResult<Book> queryByBookPage(String bookId, String bookName, Integer page, Integer rows, String sortBy, String mode) {
        // 1.初始化Example类
        Example example = new Example(Book.class);
        Example.Criteria criteria = example.createCriteria();
        // 2.添加模糊查询
        if (StringUtils.isNotBlank(bookId) || StringUtils.isNotBlank(bookName)){
            criteria.andLike("bookId","%"+bookId+"%").andLike("bookName","%"+bookName+"%");
        }
        // 3.分页
        PageHelper.startPage(page,rows);
        // 4.添加排序
        if (StringUtils.isNotBlank(sortBy)){
            example.setOrderByClause((sortBy.equals("sortId"))?"sort_id":sortBy + " " + mode);
        }
        // 5.调用通用mapper
        List<Book> books = this.bookMapper.selectByExample(example);
        books.forEach(book -> {
            Long sortId = book.getSortId();
            String sortName = this.bookMapper.queryBookSortByName(sortId);
            book.setSortName(sortName);
        });
        PageInfo<Book> info = new PageInfo<>(books);
        return new PageResult<>(info.getTotal(),info.getList());

    }

    /**
     * 新增书籍
     * @param book
     */
    @Override
    public void insertBook(Book book) {
        this.bookMapper.insert(book);
    }

    /**
     * 删除书籍
     * @param ids
     */
    @Override
    public void deleteBooks(List<Long> ids) {
        ids.forEach(id->{
            this.bookMapper.deleteByPrimaryKey(id);
        });
    }

    // 查询书籍id，name
    @Override
    public List<Book> findByIdName() {
        Example example = Example.builder(Book.class).select("id","bookId").build();
        return this.bookMapper.selectByExample(example);
    }

    // 根据id查询书籍
    @Override
    public Book queryById(Long id) {
        Book book = this.bookMapper.queryById(id);
        return book;
    }

    // 修改书籍
    @Override
    public void alterBook(Book book) {
        this.bookMapper.updateByPrimaryKey(book);
    }
}
