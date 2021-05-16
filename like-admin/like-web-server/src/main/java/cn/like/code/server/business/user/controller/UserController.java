package cn.like.code.server.business.user.controller;


import cn.like.code.server.business.user.pojo.dto.UserDTO;
import cn.like.code.server.business.user.pojo.query.UserQuery;
import cn.like.code.server.business.user.service.UserService;
import com.sika.code.result.Result;
import com.sika.code.standard.base.controller.BaseStandardController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author like @email:980650920@qq.com
 * @since 2021-05-15 07:37:04
 */
@RestController(value = "userController")
@RequestMapping("user")
public class UserController extends BaseStandardController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "save")
    public Result save(@RequestBody UserDTO userDto) {
        return super.success(userService.save(userDto));
    }

    @RequestMapping(value = "save_batch")
    public Result saveBatch(@RequestBody List<UserDTO> userDtos) {
        return super.success(userService.saveForBatch(userDtos));
    }

    @RequestMapping(value = "update_by_id")
    public Result updateById(@RequestBody UserDTO userDto) {
        return super.success(userService.updateById(userDto));
    }

    @PostMapping(value = "page")
    public Result page(@RequestBody UserQuery userQuery) {
        return super.success(userService.page(userQuery));
    }

    @RequestMapping(value = "find")
    public Result find(@RequestBody UserQuery userQuery) {
        return super.success(userService.find(userQuery));
    }

    @RequestMapping(value = "list")
    public Result list(@RequestBody UserQuery userQuery) {
        return super.success(userService.list(userQuery));
    }
}
