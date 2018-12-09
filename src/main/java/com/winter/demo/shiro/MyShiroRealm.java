package com.winter.demo.shiro;

import com.winter.demo.dao.LoginUserMapper;
import com.winter.demo.entity.LoginUser;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


//实现AuthorizingRealm接口用户用户认证
public class MyShiroRealm extends AuthorizingRealm {
@Autowired
LoginUserMapper loginUserMapper;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        String name= (String) principalCollection.getPrimaryPrincipal();


        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        //获取用户信息
        String name = authenticationToken.getPrincipal().toString();
        LoginUser loginUser = new LoginUser();
        loginUser.setUserName("士大夫");
        List<LoginUser> loginUsers = loginUserMapper.queryByLoginUser(loginUser);

        if (loginUsers == null) {
            //这里返回后会报出对应异常
            return null;
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name, loginUsers.get(0).getPassword().toString(), getName());
            return simpleAuthenticationInfo;
        }
    }

}
