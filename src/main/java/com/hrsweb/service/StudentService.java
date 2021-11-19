package com.hrsweb.service;

import com.hrsweb.pojo.PageResult;
import com.hrsweb.pojo.Student;

import java.util.List;

public interface StudentService {
    PageResult<Student> queryStudentByPage(String studentId, String name, Integer page, Integer rows, String sortBy, String mode);

    void addStudent(Student student);

    Student queryById(Long id);

    void updateStudent(Student student);

    void deleteByIds(List<Long> ids);
}
