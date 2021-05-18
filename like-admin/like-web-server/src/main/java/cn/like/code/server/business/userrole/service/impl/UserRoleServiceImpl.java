package cn.like.code.server.business.userrole.service.impl;

import cn.like.code.server.business.userrole.convert.UserRoleConvert;
import cn.like.code.server.business.userrole.entity.UserRoleEntity;
import cn.like.code.server.business.userrole.mapper.UserRoleMapper;
import cn.like.code.server.business.userrole.pojo.dto.UserRoleDTO;
import cn.like.code.server.business.userrole.service.UserRoleService;
import com.sika.code.standard.base.convert.BaseConvert;
import com.sika.code.standard.base.service.impl.BaseStandardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户和权限关联表 服务实现类
 * </p>
 *
 * @author like @email:980650920@qq.com
 * @since 2021-05-15 07:37:05
 */
@Service(value = "userRoleService")
public class UserRoleServiceImpl extends BaseStandardServiceImpl<UserRoleMapper, UserRoleEntity, UserRoleDTO> implements UserRoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;


    @Override
    protected BaseConvert<UserRoleEntity, UserRoleDTO> convert() {
        return UserRoleConvert.INSTANCE;
    }

    @Override
    public List<UserRoleEntity> listRoleIdByUserId(Long userId) {
        return userRoleMapper.selectList(
                userRoleMapper.getLambdaQuery()
                              .select(UserRoleEntity::getRoleId)
                              .eq(UserRoleEntity::getUserId, userId));
    }
}

