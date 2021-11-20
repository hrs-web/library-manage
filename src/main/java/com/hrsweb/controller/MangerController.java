package com.hrsweb.controller;

import com.hrsweb.pojo.Manger;
import com.hrsweb.service.MangerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("manger")
public class MangerController {
    @Autowired
    private MangerService mangerService;

    @GetMapping("findByIdName")
    public Map<String, List<Manger>> findByIdName(){
        List<Manger> mangers = this.mangerService.findByIdName();
        HashMap<String, List<Manger>> map = new HashMap<>();
        map.put("data",mangers);
        return map;
    }
}
