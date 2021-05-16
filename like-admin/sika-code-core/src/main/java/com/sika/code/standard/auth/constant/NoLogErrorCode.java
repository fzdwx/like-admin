package com.sika.code.standard.auth.constant;

import com.sika.code.basic.errorcode.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author daiqi
 * @create 2018-12-14 22:22
 */
@AllArgsConstructor
@Getter
public enum NoLogErrorCode implements BaseErrorCode {

    NOT_TOKEN_MESSAGE("-1", "未提供token"),

    INVALID_TOKEN_MESSAGE("-2", "token无效"),

    TOKEN_TIMEOUT_MESSAGE("-3", "token已过期"),

    BE_REPLACED_MESSAGE("-4", "token已被顶下线"),

    KICK_OUT_MESSAGE("-5", "token已被踢下线"),
    default_MESSAGE("5", "default message"),


    /** 用户名或密码有误 --- AUTH_000005 */
    USERNAME_PASSWORD_WRONG("AUTH_000005", "用户名或密码有误"),
    /** 权限不足 --- AUTHENTICATE_000001 */
    PERMISSION_DENIED("AUTHENTICATE_000001", "权限不足"),
    /** 自动授权登录失败 --- AUTH_000006 */
    AUTO_AUTHORIZED_LOGIN_FAIL("AUTH_000006", "自动授权登录失败"),
    ;
    private String code;
    private String message;
}
