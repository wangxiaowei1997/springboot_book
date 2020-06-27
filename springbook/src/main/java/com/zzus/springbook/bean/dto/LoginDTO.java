package com.zzus.springbook.bean.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wangwei
 * 2020/6/27 15:30
 * description:
 */
@Data
public class LoginDTO {

    @ApiModelProperty("token")
    private String token;
}
