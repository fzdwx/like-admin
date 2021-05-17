package cn.like.code.server.business.userrole.convert;

import cn.like.code.server.business.userrole.entity.UserRoleEntity;
import cn.like.code.server.business.userrole.pojo.dto.UserRoleDTO;
import cn.like.code.server.business.userrole.pojo.query.UserRoleQuery;
import com.sika.code.standard.base.convert.BaseConvert;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 用户和权限关联表 转换类
 * </p>
 *
 * @author like @email:980650920@qq.com
 * @since 2021-05-17 22:07:52
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserRoleConvert extends BaseConvert<UserRoleEntity, UserRoleDTO> {
    /**
     * <p>
     * 转化类的单例对象
     * </p>
     */
    UserRoleConvert INSTANCE = Mappers.getMapper(UserRoleConvert.class);

    UserRoleQuery dtoToQuery(UserRoleDTO dto);

    List<UserRoleDTO> dto2Query(List<UserRoleDTO> dtos);

}
