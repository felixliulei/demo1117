package com.winter.demo.controller;

import com.winter.demo.dao.LoginUserMapper;
import com.winter.demo.dao.UserInfoMapper;
import com.winter.demo.entity.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private LoginUserMapper loginUserMapper;

    @ResponseBody
    @RequestMapping("/getTest")
    public String getTest(){
        LoginUser loginUser = new LoginUser();
        //loginUser.setId(1);
        loginUser.setUserName("刘磊");
        //loginUser.setPassword("三十岁");

        //LoginUser loginUsers = loginUserMapper.selectByPrimaryKey(1);
        List<LoginUser> loginUsers = loginUserMapper.queryByLoginUser(loginUser);
        return loginUsers.toString();
    }

    @RequestMapping("/index")
    public  String toIndex(Model model){
        model.addAttribute("user",userInfoMapper.selectByPrimaryKey(1));
        return "index";
    }
//post登录
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestBody Map map){
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                map.get("username").toString(),
                map.get("password").toString());
        //进行验证，这里可以捕获异常，然后返回对应信息
        subject.login(usernamePasswordToken);
        return "login";
    }

}
