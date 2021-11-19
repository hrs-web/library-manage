package com.hrsweb.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

// 借书记录表
@Table(name = "borrow_books")
public class BorrowBooks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long sid;
    private Long bid;
    private Long mid;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date borrowDate;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDate;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date returnDate;
    private String illegal;
    private Integer status;
    // 注释mybatis忽略字段
    @Transient
    private String studentName;
    @Transient
    private String bookName;
    @Transient
    private String mangerName;
    @Transient
    private String statusName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Long getBid() {
        return bid;
    }

    public void setBid(Long bid) {
        this.bid = bid;
    }

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getIllegal() {
        return illegal;
    }

    public void setIllegal(String illegal) {
        this.illegal = illegal;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getMangerName() {
        return mangerName;
    }

    public void setMangerName(String mangerName) {
        this.mangerName = mangerName;
    }

    public String getStatusName() {
        if (this.getStatus()==0){
            this.statusName = "未还";
        }else if (this.getStatus()==1){
            this.statusName = "已还";
        }
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
