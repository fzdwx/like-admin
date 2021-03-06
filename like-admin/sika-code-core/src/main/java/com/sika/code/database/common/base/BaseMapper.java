package com.sika.code.database.common.base;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * Mapper基础类
 * </p>
 *
 * @author daiqi
 * @date 2019/5/8 22:57
 */
public interface BaseMapper<T> extends com.baomidou.mybatisplus.core.mapper.BaseMapper<T> {

    /**
     * 获取lambda Query
     *
     * @return {@link LambdaQueryWrapper <T>}
     */
    default com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<T> getLambdaQuery() {
        return Wrappers.<T>lambdaQuery();
    }
}
