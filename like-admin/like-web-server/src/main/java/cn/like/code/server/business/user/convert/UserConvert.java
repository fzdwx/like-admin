package cn.like.code.server.business.user.convert;

import cn.like.code.server.business.user.entity.UserEntity;
import cn.like.code.server.business.user.pojo.dto.UserDTO;
import cn.like.code.server.business.user.pojo.query.UserQuery;
import com.sika.code.standard.base.convert.BaseConvert;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 用户表 转换类
 * </p>
 *
 * @author like @email:980650920@qq.com
 * @since 2021-05-17 22:07:51
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserConvert extends BaseConvert<UserEntity, UserDTO> {
    /**
     * <p>
     * 转化类的单例对象
     * </p>
     */
    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    UserQuery dtoToQuery(UserDTO dto);

    List<UserDTO> dto2Query(List<UserDTO> dtos);

}
