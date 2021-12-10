package com.hrsweb.service;

import com.hrsweb.pojo.PageResult;
import com.hrsweb.pojo.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {
    PageResult<Student> queryStudentByPage(String studentId, String name, Integer page, Integer rows, String sortBy, String mode);

    void addStudent(Student student);

    Student queryById(Long id);

    void updateStudent(Student student);

    Map<String,String> deleteByIds(List<Long> ids);

    List<Student> findByIdName();
}
