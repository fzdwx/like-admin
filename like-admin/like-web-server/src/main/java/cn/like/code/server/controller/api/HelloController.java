package cn.like.code.server.controller.api;

import com.sika.code.common.log.annotation.ControllerLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author like
 * @date
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    @ControllerLog
    public String hello() {
        return "hello";
    }
}
