package cn.like.code.server.satoken.inter;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import cn.like.code.server.business.role.entity.RoleEntity;
import cn.like.code.server.business.role.service.RoleService;
import cn.like.code.server.business.user.constant.UserConstant;
import cn.like.code.server.business.user.service.UserService;
import cn.like.code.server.business.userrole.entity.UserRoleEntity;
import cn.like.code.server.business.userrole.service.UserRoleService;
import com.sika.code.common.util.CollectionUtil;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * loginId对应user表中的id字段
 *
 * @author: like
 * @since: 2021/5/15 18:08
 * @email: 980650920@qq.com
 * @desc: 自定义权限验证接口扩展 获取用户权限
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    final
    UserService userService;
    final
    UserRoleService userRoleService;
    final
    RoleService roleService;
    final
    StringRedisTemplate redisTemplate;

    public StpInterfaceImpl(UserService userService,
                            UserRoleService userRoleService,
                            RoleService roleService,
                            StringRedisTemplate redisTemplate) {
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.roleService = roleService;
        this.redisTemplate = redisTemplate;
    }


    /**
     * 返回一个账号所拥有的权限码集合
     * <p>
     * 对应role_key
     *
     * @param loginId  登录id
     * @param loginKey 登录关键
     * @return {@link List<String>}
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginKey) {
        SaSession session = StpUtil.getSession();
        Object sessionPermissions = session.get(UserConstant.SESSION_KEY_PERMISSIONS);

        List<String> permissions;
        if (Objects.isNull(sessionPermissions)) {
            List<UserRoleEntity> roleIds = userRoleService.listRoleIdByUserId(Long.parseLong(loginId.toString()));
            if (CollectionUtil.isEmpty(roleIds)) {
                permissions = new ArrayList<>();
            } else {
                permissions = roleService.listRoleKeyById(
                        roleIds.stream().map(UserRoleEntity::getRoleId)
                               .collect(Collectors.toSet()))
                                         .stream()
                                         .map(RoleEntity::getRoleKey)
                                         .collect(Collectors.toList());
                session.set(UserConstant.SESSION_KEY_PERMISSIONS, permissions);
            }
        } else {
            permissions = ((List<String>) sessionPermissions);
        }

        return permissions;
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     * <p>
     * 对应role_name
     *
     * @param loginId  登录id
     * @param loginKey 登录关键
     * @return {@link List<String>}
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginKey) {
        SaSession session = StpUtil.getSession();
        Object sessionRoles = session.get(UserConstant.SESSION_KEY_ROLES);
        List<String> roles;

        if (Objects.isNull(sessionRoles)) {
            List<UserRoleEntity> roleIds = userRoleService.listRoleIdByUserId((Long) loginId);
            if (CollectionUtil.isEmpty(roleIds)) {
                roles = new ArrayList<>();
            } else {
                roles = roleService.listRoleNameById(roleIds
                        .stream()
                        .map(UserRoleEntity::getRoleId)
                        .collect(Collectors.toSet()))
                                   .stream()
                                   .map(RoleEntity::getRoleName)
                                   .collect(Collectors.toList());
                session.set(UserConstant.SESSION_KEY_ROLES, roles);
            }
        } else {
            roles = ((List<String>) sessionRoles);
        }
        return roles;
    }
}
