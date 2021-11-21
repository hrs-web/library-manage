package com.hrsweb.controller;

import com.hrsweb.pojo.BookSort;
import com.hrsweb.pojo.PageResult;
import com.hrsweb.service.BookSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 删除目录节点
     * @param id
     * @return
     */
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

    /**
     * 添加分类
     * @param sort
     * @return
     */
    @PostMapping("addSort")
    public Map<String,String> addSort(@RequestBody BookSort sort){
        this.sortService.addSort(sort);
        HashMap<String,String> map = new HashMap<>();
        map.put("msg","添加成功");
        return map;
    }
}
