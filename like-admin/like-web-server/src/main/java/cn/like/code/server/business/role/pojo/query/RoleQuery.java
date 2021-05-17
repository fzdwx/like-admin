package cn.like.code.server.business.role.pojo.query;

import java.io.Serializable;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.sika.code.standard.base.pojo.query.BaseStandardQuery;

/**
 * <p>
 * 权限表 查询类
 * </p>
 *
 * @author like @email:980650920@qq.com
 * @since 2021-05-17 22:07:52
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class RoleQuery extends BaseStandardQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 数据表id
     */
    protected Long roleId;
    /**
     * 角色名称
     */
    protected String roleName;
    /**
     * 角色权限字符串
     */
    protected String roleKey;
    /**
     * 显示顺序
     */
    protected Integer roleSort;
    /**
     * id列表
     */
    protected Set<Long> ids;

}

