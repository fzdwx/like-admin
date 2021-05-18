package cn.like.code.server.controller.api;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.like.code.server.business.user.pojo.query.UserQuery;
import cn.like.code.server.business.user.service.UserService;
import cn.like.code.server.constant.UploadConstant;
import com.sika.code.result.Result;
import com.sika.code.standard.base.controller.BaseStandardController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import static cn.like.code.server.business.user.constant.UserConstant.SESSION_KEY_AVATAR;

/**
 * @author like
 * @date
 */
@RestController
public class HelloController extends BaseStandardController {

    @Autowired
    UploadConstant uploadConstant;
    @Autowired
    UserService userService;

    @PostMapping("/upload")
    public Result handleFileUpload(@RequestPart(value = "file") final MultipartFile uploadfile) throws IOException {
        return success(saveUploadedFiles(uploadfile));
    }

    private String saveUploadedFiles(final MultipartFile file) throws IOException {
        final byte[] bytes = file.getBytes();
        // TODO: 2021/5/18 type check
        String type = file.getContentType();

        // image Name
        String name = file.getOriginalFilename();
        name = new Date().getTime() + name.substring(file.getOriginalFilename().indexOf('.'));
        final Path path = Paths
                .get(uploadConstant.getImageDir() + name);
        Files.write(path, bytes);

        // 将用户图片名称保存的session中
        SaSession session = StpUtil.getSession();
        String avatarAddr = session.get(SESSION_KEY_AVATAR).toString();
        String imageName = avatarAddr.replace(uploadConstant.getUserAvatarViewPath(), "");
        // 删除原来的图片
        Files.delete(Paths.get(uploadConstant.getImageDir() + imageName));
        avatarAddr = uploadConstant.getUserAvatarViewPath() + name;
        session.set(SESSION_KEY_AVATAR, avatarAddr);

        userService.updateSelectiveById(new UserQuery().setAvatar(avatarAddr).setUserId(StpUtil.getLoginIdAsLong()));
        return name;
    }

}
