package cn.like.code.server.business.role.service.impl;

import cn.like.code.server.business.role.convert.RoleConvert;
import cn.like.code.server.business.role.entity.RoleEntity;
import cn.like.code.server.business.role.mapper.RoleMapper;
import cn.like.code.server.business.role.pojo.dto.RoleDTO;
import cn.like.code.server.business.role.service.RoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sika.code.standard.base.convert.BaseConvert;
import com.sika.code.standard.base.service.impl.BaseStandardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author like @email:980650920@qq.com
 * @since 2021-05-15 07:37:04
 */
@Service(value = "roleService")
public class RoleServiceImpl extends BaseStandardServiceImpl<RoleMapper, RoleEntity, RoleDTO> implements RoleService {
    @Autowired
    private RoleMapper roleMapper;


    @Override
    protected BaseConvert<RoleEntity, RoleDTO> convert() {
        return RoleConvert.INSTANCE;
    }

    @Override
    public List<RoleEntity> listRoleKeyById(Set<Long> roleIds) {
        roleMapper.selectList(new QueryWrapper<RoleEntity>()
                .in("id", roleIds)
                .select("role_key"));
        return roleMapper.selectBatchIds(roleIds);
    }

    @Override
    public List<RoleEntity> listRoleNameById(Set<Long> roleIds) {
        return roleMapper.selectList(new QueryWrapper<RoleEntity>()
                .in("id", roleIds)
                .select("role_name")
                .orderByAsc("role_sort"))
                ;
    }
}

