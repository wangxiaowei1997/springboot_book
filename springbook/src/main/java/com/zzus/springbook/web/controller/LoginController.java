package com.zzus.springbook.web.controller;


import com.zzus.springbook.bean.dto.LoginDTO;
import com.zzus.springbook.bean.dto.RespDTO;
import com.zzus.springbook.security.model.LoginDetail;
import com.zzus.springbook.security.model.ResultMap;
import com.zzus.springbook.security.model.TokenDetail;
import com.zzus.springbook.security.model.vo.Data;
import com.zzus.springbook.security.model.vo.RequestLoginUser;
import com.zzus.springbook.service.LoginService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @version V1.0.0
 * @Description 登陆接口
 * @Author liuyuequn weanyq@gmail.com
 * @Date 2017/10/3 1:30
 */

@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

    @Value("${token.header}")
    private String tokenHeader;


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public RespDTO login(@Valid @RequestBody RequestLoginUser requestLoginUser, BindingResult bindingResult){
        // 检查有没有输入用户名密码和格式对不对
        if (bindingResult.hasErrors()){
            return RespDTO.fail("缺少参数或者参数格式不对！");
        }

        LoginDetail loginDetail = loginService.getLoginDetail(requestLoginUser.getUsername());
        RespDTO ifLoginFail = checkAccount(requestLoginUser, loginDetail);
        if (ifLoginFail != null){
            return ifLoginFail;
        }
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setToken(loginService.generateToken((TokenDetail) loginDetail));
        return RespDTO.success(loginDTO);
    }

    private RespDTO checkAccount(RequestLoginUser requestLoginUser, LoginDetail loginDetail){
        if (loginDetail == null){
            return RespDTO.fail("账号不存在！");
        }else {
            if (!loginDetail.enable()){
                return RespDTO.fail("账号在黑名单中！");

            }
            if (!loginDetail.getPassword().equals(requestLoginUser.getPassword())){
                return RespDTO.fail("密码错误！");
            }
        }
        return null;
    }

}
