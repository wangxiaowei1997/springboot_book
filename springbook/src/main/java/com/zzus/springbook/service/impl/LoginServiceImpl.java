package com.zzus.springbook.service.impl;

import com.zzus.springbook.mapper.UserMapper;
import com.zzus.springbook.security.model.LoginDetail;
import com.zzus.springbook.security.model.TokenDetail;
import com.zzus.springbook.service.LoginService;
import com.zzus.springbook.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @version V1.0.0
 * @Description
 * @Author liuyuequn weanyq@gmail.com
 * @Date 2017/10/3 2:12
 */
@Service
public class LoginServiceImpl implements LoginService {

    private final UserMapper userMapper;
    private final TokenUtils tokenUtils;

    @Autowired
    public LoginServiceImpl(UserMapper userMapper, TokenUtils tokenUtils) {
        this.userMapper = userMapper;
        this.tokenUtils = tokenUtils;
    }

    @Override
    public LoginDetail getLoginDetail(String username) {
        return userMapper.getUserFromDatabase(username);
    }

    @Override
    public String generateToken(TokenDetail tokenDetail) {
        return tokenUtils.generateToken(tokenDetail);
    }
}
