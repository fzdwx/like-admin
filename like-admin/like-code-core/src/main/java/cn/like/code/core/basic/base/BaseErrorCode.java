package cn.like.code.core.basic.base;


import cn.like.code.core.basic.enuM.CodeEnumInf;

/**
 * @author daiqi
 * @Description: 错误代码基类
 * @date 2018/3/21 21:17
 */
public interface BaseErrorCode extends CodeEnumInf {

    String getMessage();

    default String getDesc() {
        return getMessage();
    }
}
