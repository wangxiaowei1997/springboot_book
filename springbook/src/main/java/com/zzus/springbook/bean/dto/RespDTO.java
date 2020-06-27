package com.zzus.springbook.bean.dto;

import com.zzus.springbook.enums.RespStatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangwei
 * 2020/6/27 15:19
 * description:
 */
@Data
@NoArgsConstructor
public class RespDTO<T>  {

    private int status;
    private String msg;
    private T data;


    public static <T> RespDTO<T> success() {
        RespDTO<T> resp = new RespDTO<>();
        resp.status = RespStatusEnum.SUCCESS.getStatus();
        resp.msg = RespStatusEnum.SUCCESS.getMsg();
        return resp;
    }

    public static <T> RespDTO<T> success(String msg, T data) {
        RespDTO<T> resp = new RespDTO<>();
        resp.data = data;
        resp.status = RespStatusEnum.SUCCESS.getStatus();
        resp.msg = msg;
        return resp;
    }

    public static <T> RespDTO<T> success(T data) {
        RespDTO<T> resp = new RespDTO<>();
        resp.data = data;
        resp.status = RespStatusEnum.SUCCESS.getStatus();
        resp.msg = RespStatusEnum.SUCCESS.getMsg();
        return resp;
    }

    public static <T> RespDTO<T> strongMsg(String msg, T data) {
        RespDTO<T> resp = new RespDTO<>();
        resp.data = data;
        resp.status = RespStatusEnum.STRONG_MSG.getStatus();
        resp.msg = msg;
        return resp;
    }

    public static <T> RespDTO<T> fail() {
        return fail(RespStatusEnum.FAIL.getMsg(), null);
    }

    public static <T> RespDTO<T> fail(String msg) {
        return fail(msg, null);
    }

    public static <T> RespDTO<T> fail(String msg, T data) {
        RespDTO<T> resp = new RespDTO<>();
        resp.data = data;
        resp.status = RespStatusEnum.FAIL.getStatus();
        resp.msg = msg;
        return resp;
    }

    public static <T> RespDTO<T> forbidden() {
        RespDTO<T> resp = new RespDTO<>();
        resp.status = RespStatusEnum.FORBIDDEN.getStatus();
        resp.msg = RespStatusEnum.FORBIDDEN.getMsg();
        return resp;
    }

    public static <T> RespDTO<T> pageNotFound() {
        RespDTO<T> resp = new RespDTO<>();
        resp.status = RespStatusEnum.PAGE_NOT_FOUND.getStatus();
        resp.msg = RespStatusEnum.PAGE_NOT_FOUND.getMsg();
        return resp;
    }

    public static <T> RespDTO<T> badRequest() {
        return badRequest(RespStatusEnum.BAD_REQUEST.getMsg(),null);
    }

    public static <T> RespDTO<T> badRequest(String msg) {
        return badRequest(msg, null);
    }

    public static <T> RespDTO<T> badRequest(String msg, T data) {
        RespDTO<T> resp = new RespDTO<>();
        resp.data = data;
        resp.status = RespStatusEnum.BAD_REQUEST.getStatus();
        resp.msg = msg;
        return resp;
    }

    public static <T> RespDTO<T> movedTemporarily(String msg, T data) {
        RespDTO<T> resp = new RespDTO<>();
        resp.data = data;
        resp.status = RespStatusEnum.MOVED_TEMPORARILY.getStatus();
        resp.msg = msg;
        return resp;
    }

    public static <T> RespDTO<T> error() {
        return error(RespStatusEnum.SERVER_ERROR.getMsg(), null);
    }

    public static <T> RespDTO<T> error(String msg) {
        return error(msg, null);
    }

    public static <T> RespDTO<T> error(String msg, T data) {
        RespDTO<T> resp = new RespDTO<>();
        resp.data = data;
        resp.status = RespStatusEnum.SERVER_ERROR.getStatus();
        resp.msg = msg;
        return resp;
    }

    public static <T> RespDTO<T> customStatus(RespStatusEnum respStatusEnum) {
        RespDTO<T> resp = new RespDTO<>();
        resp.status = respStatusEnum.getStatus();
        resp.msg = respStatusEnum.getMsg();
        return resp;
    }

    public static <T> RespDTO<T> customStatus(int status, String msg, T data) {
        RespDTO<T> resp = new RespDTO<>();
        resp.status = status;
        resp.msg = msg;
        resp.data = data;
        return resp;
    }
}
