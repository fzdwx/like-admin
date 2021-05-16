package cn.like.code.server.satoken;


import cn.dev33.satoken.exception.NotLoginException;
import com.sika.code.result.Result;
import com.sika.code.standard.auth.constant.NoLogErrorCode;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.sika.code.standard.auth.constant.NoLogErrorCode.INVALID_TOKEN_MESSAGE;
import static com.sika.code.standard.auth.constant.NoLogErrorCode.*;


/**
 * @author: like
 * @since: 2021/5/15 22:22
 * @email: 980650920@qq.com
 * @desc: sa token 异常
 */
@Data
@ControllerAdvice
@ConditionalOnClass(Filter.class)
@Slf4j
public class NotLoginExAdvice {

    @ExceptionHandler(NotLoginException.class)
    public Result handlerNotLoginException(NotLoginException nle, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // 打印堆栈，以供调试
        nle.printStackTrace();

        // 判断场景值，定制化异常信息
        String message = "";
        switch (nle.getType()) {
            case NotLoginException.NOT_TOKEN:
                message = "未提供token";
                break;
            case NotLoginException.INVALID_TOKEN:
                message = "token无效";
                break;
            case NotLoginException.TOKEN_TIMEOUT:
                message = "token已过期";
                break;
            case NotLoginException.BE_REPLACED:
                message = "token已被顶下线";
                break;
            case NotLoginException.KICK_OUT:
                message = "token已被踢下线";
                break;
            default:
                message = "当前会话未登录";
                break;
        }

        // 返回给前端
        return Result.newError(message);
    }

    public static NoLogErrorCode newInstance(String message) {
        if (NOT_TOKEN_MESSAGE.getMessage().equals(message)) {
            return NOT_TOKEN_MESSAGE;
        } else if (INVALID_TOKEN_MESSAGE.getMessage().equals(message)) {
            return INVALID_TOKEN_MESSAGE;
        } else if (TOKEN_TIMEOUT_MESSAGE.getMessage().equals(message)) {
            return TOKEN_TIMEOUT_MESSAGE;
        } else if (BE_REPLACED_MESSAGE.getMessage().equals(message)) {
            return BE_REPLACED_MESSAGE;
        } else if (KICK_OUT_MESSAGE.getMessage().equals(message)) {
            return KICK_OUT_MESSAGE;
        }
        return default_MESSAGE;
    }
}
