package com.winter.demo.shiro;

import com.winter.demo.dao.LoginUserMapper;
import com.winter.demo.entity.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    LoginUserMapper loginUserMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // TODO Auto-generated method stub
        System.out.println("授权");
        LoginUser user = (LoginUser) principalCollection.getPrimaryPrincipal();
        if (user != null) {
            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            //用户的角色集合
            //info.addRoles(user.getRoleStrlist());
            //用户的权限集合
            //info.addStringPermissions(user.getPerminsStrlist());

            return info;
        }
        return null;

    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        LoginUser loginUser = new LoginUser();
        loginUser.setUserName(token.getUsername());
        String password = new String((char[]) token.getCredentials());
        loginUser.setPassword(password);
        List<LoginUser> loginUsers = loginUserMapper.queryByLoginUser(loginUser);
        if (loginUsers != null && loginUsers.size() > 0) {

//            Session session = SecurityUtils.getSubject().getSession();
//            session.setAttribute("user", hasUser);//成功则放入session
            // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(loginUser, loginUser.getPassword(), getName());
            return  simpleAuthenticationInfo;
        }
        return null;

    }

}
