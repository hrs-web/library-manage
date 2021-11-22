package com.hrsweb.controller;

import com.alibaba.fastjson.JSONObject;
import com.hrsweb.pojo.Manger;
import com.hrsweb.service.MangerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    /**
     * 查询id，name
     * @return
     */
    @GetMapping("findByIdName")
    public Map<String, List<Manger>> findByIdName(){
        List<Manger> mangers = this.mangerService.findByIdName();
        HashMap<String, List<Manger>> map = new HashMap<>();
        map.put("data",mangers);
        return map;
    }

    /**
     * 登录
     * @param req
     * @return
     */
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
        HttpSession session = req.getSession();
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
        req.getSession().setAttribute("manger",manger);
        map.put("msg","登录成功");

        return map;
    }

    /**
     * 修改密码
     * @return
     */
    @PostMapping("updatePassword")
    public Map<String, Object> password(HttpServletRequest req){
        String oldPassword = null;
        String newPassword = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
            StringBuffer sb=new StringBuffer();
            String s;
            while((s=br.readLine())!=null){
                sb.append(s);
            }
            JSONObject jsonObject = JSONObject.parseObject(sb.toString());
            oldPassword = jsonObject.getString("old_password");
            newPassword = jsonObject.getString("new_password");
        } catch (IOException e) {
            e.printStackTrace();
        }
        HashMap<String, Object> map = new HashMap<>();
        HttpSession session = req.getSession();
        Manger man = (Manger) session.getAttribute("manger");
        Manger manger = this.mangerService.queryByManger(man.getUsername(),oldPassword);
        if (manger == null){
            map.put("flag",false);
            map.put("msg","旧密码输入错误！");
            return map;
        }else {
            this.mangerService.password(man.getId(),newPassword);
            session.removeAttribute("manger");
            map.put("flag",true);
            map.put("msg","修改成功，请重新登录！");
        }
        return map;
    }
}
