package com.hrsweb.controller;

import com.hrsweb.pojo.BookSort;
import com.hrsweb.pojo.PageResult;
import com.hrsweb.service.BookSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("classify")
public class BookSortController {
    @Autowired
    private BookSortService sortService;

    /**
     * 查询所有分类
     * @return
     */
    @GetMapping("page")
    public Map<String,List<BookSort>> querySortByPage(){
        List<BookSort> bookSorts = this.sortService.querySortByPage();
        Map<String,List<BookSort>> map = new HashMap<>();
        map.put("data",bookSorts);
        return map;
    }

    @GetMapping("delete")
    public PageResult deleteById(@RequestParam(value = "id") Long id){
        sortService.deleteById(id);
        return new PageResult("删除成功");
    }

    /**
     * 查询所有分类
     * @return
     */
    @GetMapping("findSort")
    public Map<String,List<BookSort>> queryBySortName(){
        List<BookSort> sorts = this.sortService.queryBySortName();
        HashMap<String, List<BookSort>> map = new HashMap<>();
        map.put("data",sorts);
        return map;
    }
}
