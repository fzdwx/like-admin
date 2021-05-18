package cn.like.code.server.constant;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: like
 * @since: 2021/5/17 19:27
 * @email: 980650920@qq.com
 * @desc:
 */
@Component
@ConfigurationProperties(prefix = "upload")
@Data
public class UploadConstant {

    /** 图片上传地址 */
    private String imageDir;

    /** 用户头像访问地址  http://localhost:8868/image/{imageName} */
    private String userAvatarViewPath;
}
