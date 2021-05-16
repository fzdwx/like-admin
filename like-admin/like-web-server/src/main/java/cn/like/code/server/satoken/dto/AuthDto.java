package cn.like.code.server.satoken.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @author: like
 * @since: 2021/5/16 8:23
 * @email: 980650920@qq.com
 * @desc: 用户登录dto
 */
@Data
@ToString
public class AuthDto {

    private String username;
    private String password;
    private String phone;
    private String phonePassword;
    private boolean rememberMe;
}
