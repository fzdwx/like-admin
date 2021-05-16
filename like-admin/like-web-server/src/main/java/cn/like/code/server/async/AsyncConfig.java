package cn.like.code.server.async;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author: like
 * @since: 2021/5/16 15:28
 * @email: 980650920@qq.com
 * @desc: 开启多线程，开启定时任务
 */
@EnableAsync
@EnableScheduling
@Configuration
public class AsyncConfig {

}
