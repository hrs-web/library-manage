package com.hrsweb.controller;

import com.hrsweb.pojo.BorrowBooks;
import com.hrsweb.pojo.PageResult;
import com.hrsweb.service.BorrowBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("history")
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
    @GetMapping("page")
    public PageResult<BorrowBooks> queryByPage(
            @RequestParam(value = "page",defaultValue = "1")Integer page,
            @RequestParam(value = "rows",defaultValue = "10")Integer rows,
            @RequestParam(value = "sortBy",required = false) String sortBy,
            @RequestParam(value = "mode",required = false) String mode){
        PageResult<BorrowBooks> result = this.borrowBookService.queryByPage(page,rows,sortBy,mode);
        return result;
    }
}
