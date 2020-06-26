package com.zzus.springbook.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zzus.springbook.entity.User;
import com.zzus.springbook.mapper.UserMapper;
import com.zzus.springbook.security.model.SecurityModelFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @version V1.0.0
 * @Description Spring Security 用于将 数据库中的用户信息转换成 userDetail 对象的服务类的实现类
 * @Author liuyuequn weanyq@gmail.com
 * @Date 2017/8/2 16:43
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * 获取 userDetail
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String key = "username_"+username;
        String userStr = stringRedisTemplate.opsForValue().get(key);
        User user;
        if (userStr != null) {
             JSONObject jsonObject = JSONObject.parseObject(userStr);
             user = JSON.parseObject(jsonObject.toJSONString(), User.class);
        } else {
             user = this.userMapper.getUserFromDatabase(username);
             JSONObject jsonObject = new JSONObject();
             jsonObject.put("username",user.getUsername());
             jsonObject.put("password",user.getPassword());
             jsonObject.put("authorities",user.getAuthorities());
             jsonObject.put("lastPasswordChange",user.getLastPasswordChange());
             jsonObject.put("enable",user.enable()?'1':'0');

             System.out.println(jsonObject);
             stringRedisTemplate.opsForValue().set(key,jsonObject.toJSONString(),60, TimeUnit.SECONDS);
        }

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return SecurityModelFactory.create(user);
        }
    }
}
