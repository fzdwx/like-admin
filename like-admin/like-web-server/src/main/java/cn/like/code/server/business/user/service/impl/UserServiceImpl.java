package cn.like.code.server.business.user.service.impl;

import cn.like.code.server.business.user.convert.UserConvert;
import cn.like.code.server.business.user.entity.UserEntity;
import cn.like.code.server.business.user.mapper.UserMapper;
import cn.like.code.server.business.user.pojo.dto.UserDTO;
import cn.like.code.server.business.user.pojo.query.UserQuery;
import cn.like.code.server.business.user.service.UserService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import com.sika.code.basic.pojo.query.BaseQuery;
import com.sika.code.database.common.Page;
import com.sika.code.database.common.PageQuery;
import com.sika.code.standard.base.convert.BaseConvert;
import com.sika.code.standard.base.service.impl.BaseStandardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author like @email:980650920@qq.com
 * @since 2021-05-15 07:37:04
 */
@Service(value = "userService")
public class UserServiceImpl extends BaseStandardServiceImpl<UserMapper, UserEntity, UserDTO> implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    protected BaseConvert<UserEntity, UserDTO> convert() {
        return UserConvert.INSTANCE;
    }

    @Override
    public boolean checkUserInfo(UserDTO userDTO) {
        return baseMapper.selectCount(Wrappers
                .<UserEntity>lambdaQuery()
                .eq(UserEntity::getUsername, userDTO.getUsername())
                .or()
                .eq(UserEntity::getPhone, userDTO.getPhone())
                .or()
                .eq(UserEntity::getEmail, userDTO.getEmail())) == 0;
    }

    @Override
    public Integer updateSelectiveById(UserQuery dtoToQuery) {
        return userMapper.updateSelectiveById(dtoToQuery);
    }

    @Override
    public <QUERY extends PageQuery> Page<UserDTO> page(QUERY query) {
        verifyQuery(query);
        int totalCount = totalCountByQuery(query);
        List<UserDTO> users;
        if (totalCount == 0) {
            users = Lists.newArrayList();
        } else {
            users = pageByQuery(query);
        }
        return new Page<>(query, totalCount, users);
    }

    @Override
    protected <QUERY extends BaseQuery> List<UserDTO> pageByQuery(QUERY query) {
        return UserConvert.INSTANCE.convertToDTOs(userMapper.pageByQuery(query));
    }

    @Override
    protected <QUERY extends BaseQuery> int totalCountByQuery(QUERY query) {
        return getMapper().totalCountByQuery(query);
    }
}

