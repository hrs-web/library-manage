package com.hrsweb.controller;

import com.hrsweb.pojo.BorrowBooks;
import com.hrsweb.pojo.PageResult;
import com.hrsweb.service.BorrowBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("borrow")
public class BorrowBookController {
    @Autowired
    private BorrowBookService borrowBookService;

    /**
     * 查询历史记录
     * @param page
     * @param rows
     * @param sortBy
     * @param mode
     * @return
     */
    @GetMapping("historyPage")
    public PageResult<BorrowBooks> queryByPage(
            @RequestParam(value = "page",defaultValue = "1")Integer page,
            @RequestParam(value = "rows",defaultValue = "10")Integer rows,
            @RequestParam(value = "sortBy",required = false) String sortBy,
            @RequestParam(value = "mode",required = false) String mode){
        PageResult<BorrowBooks> result = this.borrowBookService.queryByPage(page,rows,sortBy,mode);
        return result;
    }

    /**
     * 查询学生姓名，书籍名称，管理员姓名
     * @param sid
     * @param bid
     * @return
     */
    @GetMapping("findBook")
    public Map<String,String> findBook(Long sid, Long bid){
        Map<String,String> map = this.borrowBookService.findBook(sid,bid);
        return map;
    }

    /**
     * 借阅图书
     * @param borrowBooks
     * @return
     */
    @PostMapping("insert")
    public PageResult insertBorrow(@RequestBody BorrowBooks borrowBooks){
        this.borrowBookService.insertBorrow(borrowBooks);
        return new PageResult("借阅成功");
    }

    /**
     * 代还书籍页面
     * @param studentName
     * @param bookName
     * @param page
     * @param rows
     * @param sortBy
     * @param mode
     * @return
     */
    @GetMapping("returnPage")
    public PageResult<BorrowBooks> queryByReturnPage(
            @RequestParam(value = "studentName",required =  false)String studentName,
            @RequestParam(value = "bookName",required =  false)String bookName,
            @RequestParam(value = "page",defaultValue = "1")Integer page,
            @RequestParam(value = "rows",defaultValue = "10")Integer rows,
            @RequestParam(value = "sortBy",required = false) String sortBy,
            @RequestParam(value = "mode",required = false) String mode){
            PageResult<BorrowBooks> result = this.borrowBookService.queryByReturnPage(studentName,bookName,page,rows,sortBy,mode);
            return result;
    }

    @GetMapping("alterStatus")
    public PageResult alterByStatus(Long id){
        this.borrowBookService.alterStatus(id);
        return new PageResult("成功归还");
    }
}
