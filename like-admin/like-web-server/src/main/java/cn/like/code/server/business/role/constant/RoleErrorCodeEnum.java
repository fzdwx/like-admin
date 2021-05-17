package cn.like.code.server.business.role.constant;

import com.sika.code.basic.errorcode.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 权限表 错误枚举类
 * </p>
 *
 * @author like @email:980650920@qq.com
 * @since 2021-05-17 22:07:52
 */
@Getter
@AllArgsConstructor
public enum RoleErrorCodeEnum implements BaseErrorCode {
    /**
     * 错误枚举示例
     */
    ROLE_ERROR_CODE_ENUM("ROLE_000001", "错误枚举示例")
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
