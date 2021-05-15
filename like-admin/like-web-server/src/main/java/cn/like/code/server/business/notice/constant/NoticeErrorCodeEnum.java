package cn.like.code.server.business.notice.constant;

import com.sika.code.basic.errorcode.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 通知公告表 错误枚举类
 * </p>
 *
 * @author like @email:980650920@qq.com
 * @since 2021-05-15 07:37:05
 */
@Getter
@AllArgsConstructor
public enum NoticeErrorCodeEnum implements BaseErrorCode {
    /**
     * 错误枚举示例
     */
    NOTICE_ERROR_CODE_ENUM("NOTICE_000001", "错误枚举示例")
    ;

    /**
     * 错误码
     */
    private String code;
    /**
     * 错误信息
     */
    private String message;
}
