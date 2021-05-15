package cn.like.code.server.business.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sika.code.standard.base.pojo.entity.BaseStandardEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author like @email:980650920@qq.com
 * @since 2021-05-15 07:37:04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_user")
public class UserEntity extends BaseStandardEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 授权密码
     */
    private String oauthPwd;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 性别【1：男，2：女，0：未知】
     */
    private Integer sex;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像
     */
    private String avatar;

    /**
     * salt
     */
    private String salt;

    /**
     * 用户类型：1：游客，2：系统用户
     */
    private Integer type;


}
