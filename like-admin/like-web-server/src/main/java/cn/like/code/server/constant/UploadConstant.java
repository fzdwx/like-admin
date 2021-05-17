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

    private String imageDir;
}
