package cn.like.code.server.business.role.service;

import cn.like.code.server.business.role.entity.RoleEntity;
import cn.like.code.server.business.role.pojo.dto.RoleDTO;
import com.sika.code.standard.base.service.BaseStandardService;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author like @email:980650920@qq.com
 * @since 2021-05-15 07:37:04
 */
public interface RoleService extends BaseStandardService<RoleDTO> {

    List<RoleEntity> listRoleKeyById(Set<Long> roleIds);

    List<RoleEntity> listRoleNameById(Set<Long> collect);
}
