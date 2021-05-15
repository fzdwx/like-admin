package cn.like.code.server.endport.loginAndRegister;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.like.code.server.business.user.pojo.dto.UserDTO;
import cn.like.code.server.business.user.pojo.query.UserQuery;
import cn.like.code.server.business.user.service.UserService;
import com.google.common.base.Preconditions;
import com.sika.code.result.Result;
import com.sika.code.standard.base.controller.BaseStandardController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

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



    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return {@link Result}
     */
    @PostMapping("/auth/login")
    public Result login(String username, String password) {
        Preconditions.checkNotNull(username, "登录：用户名不能为空");
        Preconditions.checkNotNull(password, "登录：密码不能为空");
        UserDTO user = userService.find(new UserQuery().setUsername(username));
        if (Objects.isNull(user)) {
            return fail("用户不存在:" + username);
        }
        if (!BCrypt.hashpw(password, user.getSalt()).equals(user.getPassword())) {
            return fail("用户密码错误:" + username);
        }

        StpUtil.setLoginId(user.getId());

        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();



        log.info("[login] 用户登录成功:{}", tokenInfo);
        return success(tokenInfo.getTokenValue());
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

        return success(userDTO);
    }

}
