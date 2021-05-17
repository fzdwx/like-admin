package cn.like.code.server.controller.api;

import cn.like.code.server.constant.UploadConstant;
import com.sika.code.common.spring.SpringUtil;
import com.sika.code.common.util.PropUtil;
import com.sika.code.standard.auth.properties.AuthProperties;
import com.sika.code.standard.base.controller.BaseStandardController;
import com.sika.code.standard.token.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * @author like
 * @date
 */
@RestController
public class HelloController extends BaseStandardController {

    @Autowired
    UploadConstant uploadConstant;

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

    @PostMapping("/upload")
    public String handleFileUpload(@RequestPart(value = "file") final MultipartFile uploadfile) throws IOException {
        return saveUploadedFiles(uploadfile);
    }

    private String saveUploadedFiles(final MultipartFile file) throws IOException {
        final byte[] bytes = file.getBytes();
        final Path path = Paths.get( uploadConstant.getImageDir()+ file.getOriginalFilename());
        Files.write(path, bytes);

        return "ok";
    }

}
