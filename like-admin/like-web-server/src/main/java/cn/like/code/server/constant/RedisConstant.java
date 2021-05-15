package cn.like.code.server.constant;

/**
 * @author: like
 * @since: 2021/5/15 20:16
 * @email: 980650920@qq.com
 * @desc: redis key 常量
 */
public class RedisConstant {
    public static final boolean BitTrue = true;

    public static class UserConstant {
        /** 前缀 */
        public static final String KEY_LOGIN_PREFIX = "user";

        /** 功能：UV统计 user:active:20210515 */
        public static final String KEY_ACCESS_BODY = "access";
    }
}
