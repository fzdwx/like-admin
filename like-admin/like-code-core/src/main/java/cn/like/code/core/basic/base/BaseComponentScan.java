package cn.like.code.core.basic.base;

/**
 * <p>
 * 基础包扫描常量类
 * </p>
 *
 * <pre>
 *  说明：所有项目的组件扫描最终都必须继承此类
 *  约定：可以提供项目的公共组件扫描类来进行统一管理
 *  命名规范：通过项目+ComponentScan后缀,如类（TaobaoComponentScan）
 *  使用示例：TaobaoComponentScan.COM_TAOBAO
 * </pre>
 *
 * @author like
 * @date 2021/5/18 16:35
 */
public class BaseComponentScan {


    /** 扫描基础包名(扫描该包下所有组件) */
    public static final String COM_EASY_CLOUD = "cn.like.code";
}
