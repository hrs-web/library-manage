package com.hrsweb.service.impl;

import com.hrsweb.mapper.MangerMapper;
import com.hrsweb.pojo.Manger;
import com.hrsweb.service.MangerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class MangerServiceImpl implements MangerService {
    @Autowired
    private MangerMapper mangerMapper;

    @Override
    public List<Manger> findByIdName() {
        Example example = Example.builder(Manger.class).select("id","mangerName").build();
        return this.mangerMapper.selectByExample(example);
    }

    @Override
    public Manger login(String username, String password) {
        return this.mangerMapper.login(username,password);
    }

    @Override
    public Manger queryByManger(String username, String oldPassword) {
        return this.mangerMapper.queryByManger(username,oldPassword);
    }

    @Override
    public void password(Long id, String newPassword) {
        this.mangerMapper.password(id,newPassword);
    }
}
