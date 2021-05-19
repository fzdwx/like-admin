package cn.like.code.server.satoken.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import cn.like.code.server.business.user.pojo.dto.UserDTO;
import cn.like.code.server.business.user.pojo.query.UserQuery;
import cn.like.code.server.business.user.service.UserService;
import cn.like.code.server.constant.TokenConstant;
import cn.like.code.server.satoken.dto.AuthDto;
import com.google.common.base.Preconditions;
import com.sika.code.result.Result;
import com.sika.code.standard.base.controller.BaseStandardController;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Objects;

import static cn.like.code.server.business.user.constant.UserConstant.SESSION_KEY_AVATAR;

/**
 * loginId对应user表中的id字段
 *
 * @author: like
 * @since: 2021/5/15 18:04
 * @email: 980650920@qq.com
 * @desc: 登录接口
 */
@RestController
@Slf4j
public class LoginController extends BaseStandardController {

    @Autowired
    UserService userService;
    @Autowired
    StpInterface stpInterface;

    /**
     * 登录
     *
     * @param authDto auth dto
     * @return {@link Result}
     */
    @PostMapping("/auth/login")
    public Result login(@RequestBody AuthDto authDto) {
        Preconditions.checkNotNull(authDto.getUsername(), "登录：用户名不能为空");
        Preconditions.checkNotNull(authDto.getUsername(), "登录：密码不能为空");
        String username = authDto.getUsername();
        UserDTO user = userService.find(new UserQuery().setUsername(username));
        if (Objects.isNull(user)) {
            return fail("用户不存在:" + username);
        }
        if (!BCrypt.hashpw(authDto.getPassword(), user.getSalt()).equals(user.getPassword())) {
            return fail("用户密码错误:" + username);
        }
        // 调用 satoken 登录当前用户
        StpUtil.setLoginId(user.getId());
        if (StringUtils.isNotBlank(user.getAvatar())) {
            StpUtil.getSession().set(SESSION_KEY_AVATAR, user.getAvatar());
        }
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();

        log.info("[login] 用户登录成功:{}-{}", username, tokenInfo.getTokenValue());
        return success(new HashMap<String, Object>() {
            {
                put(TokenConstant.userRoleName, stpInterface.getRoleList(user.getId(), tokenInfo.getLoginKey()).get(0));
                put(TokenConstant.tokenName, tokenInfo.getTokenValue());
            }
        });
    }

    /**
     * 注册
     *
     * @param userDTO 用户dto
     * @return {@link Result}
     */
    @PostMapping("/auth/register")
    public Result register(UserDTO userDTO) {
        Preconditions.checkNotNull(userDTO.getUsername(), "注册:用户名不能为空");
        Preconditions.checkNotNull(userDTO.getPassword(), "注册:密码不能为空");
        if (!userService.checkUserInfo(userDTO)) {
            return fail("用户信息重复");
        }
        String salt = BCrypt.gensalt();
        userDTO.setPassword(BCrypt.hashpw(userDTO.getPassword(), salt));
        userDTO.setSalt(salt);
        userService.save(userDTO);

        log.info("[register] 用户注册:{}", userDTO);

        return success(userDTO.getUsername());
    }

}
