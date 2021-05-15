package cn.like.code.server.business.role.controller;


import cn.like.code.server.business.role.pojo.dto.RoleDTO;
import cn.like.code.server.business.role.pojo.query.RoleQuery;
import cn.like.code.server.business.role.service.RoleService;
import com.sika.code.result.Result;
import com.sika.code.standard.base.controller.BaseStandardController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author like @email:980650920@qq.com
 * @since 2021-05-15 07:37:04
 */
@RestController(value = "roleController")
@RequestMapping("role")
public class RoleController extends BaseStandardController {
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "save")
    public Result save(@RequestBody RoleDTO roleDto) {
        return super.success(roleService.save(roleDto));
    }

    @RequestMapping(value = "save_batch")
    public Result saveBatch(@RequestBody List<RoleDTO> roleDtos) {
        return super.success(roleService.saveForBatch(roleDtos));
    }

    @RequestMapping(value = "update_by_id")
    public Result updateById(@RequestBody RoleDTO roleDto) {
        return super.success(roleService.updateById(roleDto));
    }

    @RequestMapping(value = "page")
    public Result page(@RequestBody RoleQuery roleQuery) {
        return super.success(roleService.page(roleQuery));
    }

    @RequestMapping(value = "find")
    public Result find(@RequestBody RoleQuery roleQuery) {
        return super.success(roleService.find(roleQuery));
    }

    @RequestMapping(value = "list")
    public Result list(@RequestBody RoleQuery roleQuery) {
        return super.success(roleService.list(roleQuery));
    }
}
