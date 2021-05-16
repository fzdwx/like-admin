package cn.like.code.server.satoken.config;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.router.SaRouterUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.like.code.server.satoken.NotLoginExAdvice;
import com.sika.code.common.json.util.JSONUtil;
import com.sika.code.result.generator.ResultGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

/**
 * 注解鉴权 —— 优雅的将鉴权与业务代码分离！
 * <br>
 */
@Configuration
@Slf4j
public class SaTokenConfigure implements WebMvcConfigurer {

    /*
        // 登录认证：当前会话必须登录才能通过
        @SaCheckLogin
        @RequestMapping("info")
        public String info() {
            return "查询用户信息";
        }

        // 角色认证：当前会话必须具有指定角色标识才能通过
        @SaCheckRole("super-admin")
        @RequestMapping("add")
        public String add() {
            return "用户增加";
        }

        // 权限认证：当前会话必须具有指定权限才能通过
        @SaCheckPermission("user-add")
        @RequestMapping("add")
        public String add() {
            return "用户增加";
        }

         鉴权
         @SaCheckLogin: 标注在方法或类上，当前会话必须处于登录状态才可通过校验 <br>
         @SaCheckRole("admin"): 标注在方法或类上，当前会话必须具有指定角色标识才能通过校验   <br>
         @SaCheckPermission("user:add"): 标注在方法或类上，当前会话必须具有指定权限才能通过校验 <br>

         // 注解式鉴权：只要具有其中一个权限即可通过校验  mode = SaMode.OR
         @RequestMapping("atJurOr")
         @SaCheckPermission(value = {"user-add", "user-all", "user-delete"}, mode = SaMode.OR)
         public AjaxJson atJurOr() {
             return AjaxJson.getSuccessData("用户信息");
         }
     */

    /**
     * 注册 [sa-token全局过滤器]
     */
    @Bean
    public SaServletFilter getSaServletFilter() {
        return new SaServletFilter()

                // 指定 拦截路由 与 放行路由
                .addInclude("/**").addExclude("/favicon.ico")

                // 认证函数: 每次请求执行
                .setAuth(r -> {
                    // 登录验证 -- 拦截所有路由，并排除/user/doLogin 用于开放登录
                    // 登录验证 -- 排除多个路径
                    SaRouterUtil.match(
                            Arrays.asList("/**"),
                            Arrays.asList("/auth/login", "/auth/register"),
                            StpUtil::checkLogin);

                    // 角色认证 -- 拦截以 admin 开头的路由，必须具备[admin]角色或者[super-admin]角色才可以通过认证
                    SaRouterUtil.match("/admin/**", () -> StpUtil.checkRoleOr("admin", "super-admin"));

                    // 权限认证 -- 不同模块, 校验不同权限
                    SaRouterUtil.match("/user/**", () -> StpUtil.checkPermission("user"));
                    SaRouterUtil.match("/admin/**", () -> StpUtil.checkPermission("admin"));
                    SaRouterUtil.match("/goods/**", () -> StpUtil.checkPermission("goods"));
                    SaRouterUtil.match("/orders/**", () -> StpUtil.checkPermission("orders"));
                    SaRouterUtil.match("/notice/**", () -> StpUtil.checkPermission("notice"));
                    SaRouterUtil.match("/comment/**", () -> StpUtil.checkPermission("comment"));

                    // 匹配 restful 风格路由
                    SaRouterUtil.match("/article/get/{id}", () -> StpUtil.checkPermission("article"));

                })

                // 异常处理函数：每次认证函数发生异常时执行此函数
                .setError(e -> {
                    // 不能只打印message
                    e.printStackTrace();
                    return JSONUtil.toJSONString(new ResultGenerator().generateResultError(NotLoginExAdvice.newInstance(e.getMessage())));
                })

                // 前置函数：在每次认证函数之前执行
                .setBeforeAuth(r -> {
                    // ---------- 设置一些安全响应头 ----------
                    SaHolder.getResponse()
                            // 服务器名称
                            .setServer("like-admin-server")
                            // 是否可以在iframe显示视图： DENY=不可以 | SAMEORIGIN=同域下可以 | ALLOW-FROM uri=指定域名下可以
                            .setHeader("X-Frame-Options", "SAMEORIGIN")
                            // 是否启用浏览器默认XSS防护： 0=禁用 | 1=启用 | 1; mode=block 启用, 并在检查到XSS攻击时，停止渲染页面
                            .setHeader("X-Frame-Options", "1; mode=block")
                            // 禁用浏览器内容嗅探
                            .setHeader("X-Content-Type-Options", "nosniff")
                    ;
                });
    }
}