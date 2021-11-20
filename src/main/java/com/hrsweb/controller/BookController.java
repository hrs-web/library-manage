package com.hrsweb.controller;

import com.hrsweb.pojo.Book;
import com.hrsweb.pojo.PageResult;
import com.hrsweb.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("book")
public class BookController {
    @Autowired
    private BookService bookService;

    /**
     * 组合查询书籍信息
     * @param bookId
     * @param bookName
     * @param page
     * @param rows
     * @param sortBy
     * @param mode
     * @return
     */
    @GetMapping("page")
    public PageResult<Book> queryByBookPage(
            @RequestParam(value = "bookId",required =  false)String bookId,
            @RequestParam(value = "bookName",required =  false)String  bookName,
            @RequestParam(value = "page",defaultValue = "1")Integer page,
            @RequestParam(value = "rows",defaultValue = "10")Integer rows,
            @RequestParam(value = "sortBy",required = false) String sortBy,
            @RequestParam(value = "mode",required = false) String mode){
        PageResult<Book> result = this.bookService.queryByBookPage(bookId,bookName,page,rows,sortBy,mode);
        return result;
    }

    /**
     * 添加书籍
     * @param book
     * @return
     */
    @PostMapping("addBook")
    public PageResult insertBook(@RequestBody Book book){
        this.bookService.insertBook(book);
        return new PageResult("添加成功");
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @GetMapping("deleteIds")
    public PageResult deleteBooks(@RequestParam(value = "ids") List<Long> ids){
        this.bookService.deleteBooks(ids);
        return new PageResult("删除成功");
    }

    /**
     * 查找id，name
     * @return
     */
    @GetMapping("findByIdName")
    public Map<String,List<Book>> findByIdName(){
        List<Book> books = this.bookService.findByIdName();
        HashMap<String, List<Book>> map = new HashMap<>();
        map.put("data",books);
        return map;
    }

}
