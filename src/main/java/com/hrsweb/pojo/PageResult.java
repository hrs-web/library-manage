package com.hrsweb.pojo;

import java.util.List;

// 分页工具
public class PageResult<T> {
    private String msg;
    private Long total;
    private Long totalPage;
    private List<T> data;

    public PageResult(){
    }

    public PageResult(String msg){
        this.msg = msg;
    }

    public PageResult(Long total,List<T> data){
        this.total = total;
        this.data = data;
    }

    public PageResult(Long total,Long totalPage,List<T> data){
        this.total = total;
        this.totalPage = totalPage;
        this.data = data;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
