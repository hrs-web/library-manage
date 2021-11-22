package com.hrsweb.service;

import com.hrsweb.pojo.Manger;

import java.util.List;

public interface MangerService {
    List<Manger> findByIdName();

    Manger login(String username, String password);

    Manger queryByManger(String username, String oldPassword);

    void password(Long id, String newPassword);
}
