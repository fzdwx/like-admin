package cn.like.code.server.business.user.mapper;

import cn.like.code.server.business.user.entity.UserEntity;
import cn.like.code.server.business.user.pojo.query.UserQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.sika.code.standard.base.basemapper.BaseStandardMapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author like @email:980650920@qq.com
 * @since 2021-05-17 22:07:51
 */
@Repository
public interface UserMapper extends BaseStandardMapper<UserEntity> {

    Integer updateById(@Param("query") UserQuery query,@Param("query") UserQuery q2);
}
