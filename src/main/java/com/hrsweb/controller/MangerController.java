package com.hrsweb.controller;

import com.alibaba.fastjson.JSONObject;
import com.hrsweb.pojo.Manger;
import com.hrsweb.service.MangerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("manger")
public class MangerController {
    @Autowired
    private MangerService mangerService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @GetMapping("findByIdName")
    public Map<String, List<Manger>> findByIdName(){
        List<Manger> mangers = this.mangerService.findByIdName();
        HashMap<String, List<Manger>> map = new HashMap<>();
        map.put("data",mangers);
        return map;
    }

    @PostMapping("login")
    public Map<String,Object> login(HttpServletRequest req){
        String username = null;
        String password = null;
        String captcha = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
            StringBuffer sb=new StringBuffer();
            String s;
            while((s=br.readLine())!=null){
                sb.append(s);
            }
            JSONObject jsonObject = JSONObject.parseObject(sb.toString());
            username = jsonObject.getString("username");
            password = jsonObject.getString("password");
            captcha = jsonObject.getString("captcha");
        } catch (IOException e) {
            e.printStackTrace();
        }
        HashMap<String, Object> map = new HashMap<>();
        // 1，效验验证码
        HttpSession session = request.getSession();
        String checkCode = (String)session.getAttribute("CHECK_CODE");
        session.removeAttribute("CHECK_CODE");
        if (checkCode == null || !checkCode.equalsIgnoreCase(captcha)){
            // 响应验证码错误
            map.put("flag",false);
            map.put("msg","验证码错误");
            return map;
        }
        Manger manger = this.mangerService.login(username,password);
        if (manger == null){
            // 响应账号或密码错误
            map.put("flag",false);
            map.put("msg","账号或密码错误");
            return map;
        }
        request.getSession().setAttribute("manger",manger);
        map.put("msg","登录成功");

        return map;
    }
}
