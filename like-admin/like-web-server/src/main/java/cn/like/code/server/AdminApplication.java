package cn.like.code.server;

import cn.dev33.satoken.SaManager;
import com.sika.code.basic.constant.BaseComponentScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


/**
 * 启动类
 * <p>
 * exclude = {DataSourceAutoConfiguration.class}非常重要，如果没有关闭，在启动程序时会发生循环依赖的错误
 *
 * @author like
 */
@SpringBootApplication(scanBasePackages = {BaseComponentScan.COM_EASY_CLOUD, "cn.like.code.server", "cn.hutool.extra.spring"}, exclude = {DataSourceAutoConfiguration.class})
@MapperScan({"cn.like.code.server.**.mapper"})
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);

        System.out.println("启动成功：sa-token配置如下：" + SaManager.getConfig());

    }
}
