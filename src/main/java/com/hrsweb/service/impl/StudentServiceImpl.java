package com.hrsweb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hrsweb.mapper.StudentMapper;
import com.hrsweb.pojo.PageResult;
import com.hrsweb.pojo.Student;
import com.hrsweb.service.StudentService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 组合查询学生信息
     * @param studentId
     * @param name
     * @param page
     * @param rows
     * @param sortBy
     * @param mode
     * @return
     */
    @Override
    public PageResult<Student> queryStudentByPage(String studentId, String name, Integer page, Integer rows, String sortBy, String mode) {
        // 1.初始化Example对象
        Example example = new Example(Student.class);
        Example.Criteria criteria = example.createCriteria();
        // 2.添加模糊查询
        if (StringUtils.isNotBlank(studentId) || StringUtils.isNotBlank(name)){
            criteria.andLike("studentId","%"+studentId+"%").andLike("name","%"+name+"%");
        }
        // 3.添加分页
        PageHelper.startPage(page,rows);
        // 4.添加排序
        if (StringUtils.isNotBlank(sortBy)){
            example.setOrderByClause((sortBy.equals("studentId"))?"student_id":sortBy + " " + mode); //
        }
        // 5.调用mapper方式
        List<Student> students = this.studentMapper.selectByExample(example);
        PageInfo<Student> info = new PageInfo<>(students);

        return new PageResult<>(info.getTotal(),info.getList());
    }

    // 新增学生
    @Override
    public void addStudent(Student student) {
        this.studentMapper.insertSelective(student); // insertSelective：只给有值的字段插入
    }

    // 根据id查询学生
    @Override
    public Student queryById(Long id) {
        return this.studentMapper.selectByPrimaryKey(id);
    }

    // 修改
    @Override
    public void updateStudent(Student student) {
        this.studentMapper.updateByPrimaryKeySelective(student);
    }

    // 删除
    @Override
    public void deleteByIds(List<Long> ids) {
        ids.forEach(id->{
            this.studentMapper.deleteByPrimaryKey(id);
        });
    }

    @Override
    public List<Student> findByIdName() {
        Example example = Example.builder(Student.class).select("id","studentId").build();
        return this.studentMapper.selectByExample(example);
    }
}
