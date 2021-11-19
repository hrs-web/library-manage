package com.hrsweb.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// 分类表
@Table(name = "book_sort")
public class BookSort {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long pid;  // 父id
    private String sortName;  // 分类名
    private String description;  // 描述

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
