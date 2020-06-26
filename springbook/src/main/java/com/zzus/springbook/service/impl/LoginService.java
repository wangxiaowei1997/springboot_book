package com.zzus.springbook.service.impl;

import com.zzus.springbook.mapper.UserMapper;
import com.zzus.springbook.security.model.LoginDetail;
import com.zzus.springbook.security.model.TokenDetail;
import com.zzus.springbook.utils.TokenUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @version V1.0.0
 * @Description
 * @Author liuyuequn weanyq@gmail.com
 * @Date 2017/10/3 2:12
 */
@Service
public class LoginService {

    @Resource
    private  UserMapper userMapper;
    @Resource
    private  TokenUtils tokenUtils;



    public LoginDetail getLoginDetail(String username) {
        return userMapper.getUserFromDatabase(username);
    }

    public String generateToken(TokenDetail tokenDetail) {
        return tokenUtils.generateToken(tokenDetail);
    }
}
