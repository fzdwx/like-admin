package cn.like.code.server.business.role.convert;

import cn.like.code.server.business.role.entity.RoleEntity;
import cn.like.code.server.business.role.pojo.dto.RoleDTO;
import cn.like.code.server.business.role.pojo.query.RoleQuery;
import com.sika.code.standard.base.convert.BaseConvert;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 权限表 转换类
 * </p>
 *
 * @author like @email:980650920@qq.com
 * @since 2021-05-17 22:07:52
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleConvert extends BaseConvert<RoleEntity, RoleDTO> {
    /**
     * <p>
     * 转化类的单例对象
     * </p>
     */
    RoleConvert INSTANCE = Mappers.getMapper(RoleConvert.class);

    RoleQuery dtoToQuery(RoleDTO dto);

    List<RoleDTO> dto2Query(List<RoleDTO> dtos);

}
