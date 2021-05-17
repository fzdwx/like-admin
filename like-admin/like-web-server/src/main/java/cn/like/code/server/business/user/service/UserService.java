package cn.like.code.server.business.user.service;

import cn.like.code.server.business.user.pojo.dto.UserDTO;
import cn.like.code.server.business.user.pojo.query.UserQuery;
import com.sika.code.standard.base.service.BaseStandardService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author like @email:980650920@qq.com
 * @since 2021-05-15 07:37:04
 */
public interface UserService extends BaseStandardService<UserDTO> {

    boolean checkUserInfo(UserDTO userDTO);

    Integer updateById(UserQuery dtoToQuery);
}
