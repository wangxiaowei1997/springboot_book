package com.zzus.springbook.service;


import com.zzus.springbook.security.model.LoginDetail;
import com.zzus.springbook.security.model.TokenDetail;

/**
 * @version V1.0.0
 * @Description
 * @Author liuyuequn weanyq@gmail.com
 * @Date 2017/10/3 2:11
 */
public interface LoginService {

    LoginDetail getLoginDetail(String username);

    String generateToken(TokenDetail tokenDetail);

}
