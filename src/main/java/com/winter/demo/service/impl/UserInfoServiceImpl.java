package com.winter.demo.service.impl;

import com.winter.demo.dao.UserInfoMapper;
import com.winter.demo.entity.UserInfo;
import com.winter.demo.service.UserInfoService;

public class UserInfoServiceImpl implements UserInfoService {
    private UserInfoMapper userInfoMapper;

    @Override
    public String getUserInfoById() {
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(1);

        return null;
    }
}
