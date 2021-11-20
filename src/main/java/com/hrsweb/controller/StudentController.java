package com.hrsweb.controller;

import com.hrsweb.pojo.PageResult;
import com.hrsweb.pojo.Student;
import com.hrsweb.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * 查询所有学生信息
     * @param studentId
     * @param name
     * @param page
     * @param rows
     * @param sortBy
     * @param mode
     * @return
     */
    @GetMapping("page")
    public ResponseEntity<PageResult<Student>> queryStudentByPage(
            @RequestParam(value = "studentId",required =  false)String studentId,
            @RequestParam(value = "name",required =  false)String name,
            @RequestParam(value = "page",defaultValue = "1")Integer page,
            @RequestParam(value = "rows",defaultValue = "10")Integer rows,
            @RequestParam(value = "sortBy",required = false) String sortBy,
            @RequestParam(value = "mode",required = false) String mode){
        PageResult<Student> result =this.studentService.queryStudentByPage(studentId,name,page,rows,sortBy,mode);
        // 判断有没有查到数据，没有返回404
        if (CollectionUtils.isEmpty(result.getData())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    /**
     * 新增学生。
     * 注意：$.ajax访问必须要有返回值
     * @param student
     * @return
     */
    @PostMapping("add")
    @ResponseBody
    public PageResult addStudent(@RequestBody Student student){
        this.studentService.addStudent(student);
        return new PageResult();
    }

    /**
     * 根据id查询学生信息
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public ResponseEntity<Student> queryById(@PathVariable("id")Long id){
        Student student = this.studentService.queryById(id);
        if (student.getId()==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(student);
    }

    /**
     * 修改
     * @param student
     * @return
     */
    @PostMapping("update")
    public PageResult updateStudent(@RequestBody Student student) {
        this.studentService.updateStudent(student);
        return new PageResult();
    }

    /**
     * 根据ids删除学生
     * @param ids
     * @return
     */
    @GetMapping("deleteIds")
    public PageResult deleteByIds(@RequestParam(value = "ids") List<Long> ids){
        this.studentService.deleteByIds(ids);
        return new PageResult();
    }

    /**
     * 查询
     * @return
     */
    @GetMapping("findByIdName")
    public Map<String,List<Student>> findByIdName(){
        List<Student> students = this.studentService.findByIdName();
        HashMap<String,List<Student>> map = new HashMap<>();
        map.put("data",students);
        return map;
    }

}
