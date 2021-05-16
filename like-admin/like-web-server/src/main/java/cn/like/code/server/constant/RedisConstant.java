package cn.like.code.server.constant;

/**
 * @author: like
 * @since: 2021/5/15 20:16
 * @email: 980650920@qq.com
 * @desc: redis key 常量
 * @see com.sika.code.cache.redis.util.RedisUtil#generateRedisKey(String, Object, String...)
 */
public class RedisConstant {
    public static final boolean BitTrue = true;

    public static class UserConstant {
        /** 前缀 */
        public static final String KEY_LOGIN_PREFIX = "user";

        /** 功能：UV统计 user:active:20210515 */
        public static final String KEY_ACCESS_BODY = "access";
    }

    public static class SaToken {
        /** 前缀 */
        public static final String KEY_SATOKEN_PREFIX = TokenConstant.tokenName;

        /** 功能：用户登录 */
        public static final String KEY_LOGIN_BODY = "login";

        /** 功能：用户token auth:login:token:{tokenValue} */
        public static final String KEY_TOKEN_BODY = "token";

        /** 功能：用户session  auth:login:session:{loginId} */
        public static final String KEY_SESSION_BODY = "session";
    }
}
