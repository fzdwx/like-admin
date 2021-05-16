package cn.like.code.server.controller.api;

import com.sika.code.standard.auth.properties.AuthProperties;
import com.sika.code.standard.base.controller.BaseStandardController;
import com.sika.code.standard.token.util.JwtTokenUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author like
 * @date
 */
@RestController
public class HelloController extends BaseStandardController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping(value = "/api/auth/login")
    public String login(String user, String password) {
        Map map = new HashMap();
        map.put("user", user);
        map.put("password", password);
        return JwtTokenUtil.generateToken(map, AuthProperties.jwtSecret);
    }

}
