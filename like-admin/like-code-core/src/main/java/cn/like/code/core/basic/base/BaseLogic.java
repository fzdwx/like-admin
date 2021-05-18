package cn.like.code.core.basic.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * 业务逻辑基础类
 * </p>
 *
 * <pre>
 *  说明：所有业务逻辑类可以继承此类
 *  约定：
 *  命名规范：以logic结尾
 *  使用示例：
 * </pre>
 *
 * @author like
 * @date 2021/5/18 16:56
 */
public class BaseLogic {
    protected Logger log = LoggerFactory.getLogger(this.getClass());
}
