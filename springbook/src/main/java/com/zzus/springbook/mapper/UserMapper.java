package com.zzus.springbook.mapper;

import com.zzus.springbook.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    User getUserFromDatabase(@Param("username") String username);

}
