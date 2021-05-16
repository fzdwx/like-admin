package cn.like.code.server.satoken.listener;

import cn.dev33.satoken.listener.SaTokenListener;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.hutool.core.date.DatePattern;
import cn.like.code.server.constant.RedisConstant;
import com.sika.code.cache.redis.util.RedisUtil;
import com.sika.code.common.date.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

import static cn.like.code.server.constant.RedisConstant.BitTrue;

/**
 * @author: like
 * @since: 2021/5/15 21:00
 * @email: 980650920@qq.com
 * @desc: 用户登录状态侦听器
 */
@Component
@Slf4j
public class UserLogInStatusListener implements SaTokenListener {

    @Autowired
    StringRedisTemplate redis;

    @Override
    public void doLogin(String loginKey, Object loginId, SaLoginModel loginModel) {
        // 记录用户UV
        redis.opsForValue().setBit(
                Objects.requireNonNull(RedisUtil.generateRedisKey(
                        RedisConstant.UserConstant.KEY_LOGIN_PREFIX,
                        RedisConstant.UserConstant.KEY_ACCESS_BODY,
                        DateUtil.format(new Date(), DatePattern.PURE_DATE_PATTERN))),
                Long.parseLong(loginId.toString()), BitTrue);
    }

    /** 每次注销时触发 */
    @Override
    public void doLogout(String loginKey, Object loginId, String tokenValue) {
        // del token
        redis.delete(Objects.requireNonNull(RedisUtil.generateRedisKey(
                RedisConstant.SaToken.KEY_SATOKEN_PREFIX,
                RedisConstant.SaToken.KEY_LOGIN_BODY, RedisConstant.SaToken.KEY_TOKEN_BODY,
                tokenValue)));
        log.info("[login] 用户注销:{}-{}",loginId, tokenValue);
    }

    /** 每次被踢下线时触发 */
    @Override
    public void doLogoutByLoginId(String loginKey, Object loginId, String tokenValue, String device) {

    }

    /** 每次被顶下线时触发 */
    @Override
    public void doReplaced(String loginKey, Object loginId, String tokenValue, String device) {
        // del token
        redis.delete(Objects.requireNonNull(RedisUtil.generateRedisKey(
                RedisConstant.SaToken.KEY_SATOKEN_PREFIX,
                RedisConstant.SaToken.KEY_LOGIN_BODY, RedisConstant.SaToken.KEY_TOKEN_BODY,
                tokenValue)));
        log.info("[login] 用户被顶下线:{}-{}",loginId, tokenValue);
    }

    /** 每次被封禁时触发 */
    @Override
    public void doDisable(String loginKey, Object loginId, long disableTime) {
        // ...
    }

    /** 每次被解封时触发 */
    @Override
    public void doUntieDisable(String loginKey, Object loginId) {
        // ...
    }

    /** 每次创建Session时触发 */
    @Override
    public void doCreateSession(String id) {
        // ...
    }

    /** 每次注销Session时触发 */
    @Override
    public void doLogoutSession(String id) {
        // ...
    }
}
