package cn.like.code.server.satoken.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author: like
 * @since: 2021/5/16 12:58
 * @email: 980650920@qq.com
 * @desc:
 */
@Component
public class TokenConstant {

    public static String tokenName="auth";

    public static final String userRoleName = "user_role";

}
