package cn.like.code.server.business.user.controller;


import cn.like.code.server.business.user.convert.UserConvert;
import cn.like.code.server.business.user.pojo.dto.UserDTO;
import cn.like.code.server.business.user.pojo.query.UserQuery;
import cn.like.code.server.business.user.service.UserService;
import cn.like.code.server.constant.UploadConstant;
import com.sika.code.database.common.Page;
import com.sika.code.result.Result;
import com.sika.code.standard.base.controller.BaseStandardController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author like @email:980650920@qq.com
 * @since 2021-05-17 22:07:51
 */
@RestController(value = "userController")
@RequestMapping("user")
public class UserController extends BaseStandardController {
    @Autowired
    private UserService userService;
    @Autowired
    UploadConstant uploadConstant;

    @RequestMapping(value = "save")
    public Result save(@RequestBody UserDTO userDto) {
        return super.success(userService.save(userDto));
    }

    @RequestMapping(value = "save_batch")
    public Result saveBatch(@RequestBody List<UserDTO> userDtos) {
        return super.success(userService.saveForBatch(userDtos));
    }

    @RequestMapping(value = "update_by_id")
    public Result updateById(@RequestBody UserDTO userDto) throws IOException {
        if (Objects.isNull(userDto.getId())) {
            return fail("用户id不能为空");
        }
        UserQuery query = UserConvert.INSTANCE.dtoToQuery(userDto);
        query.setUserId(userDto.getId());
        query.setAvatar("");
        return super.success(userService.updateSelectiveById(query));
    }

    @RequestMapping(value = "page")
    public Result page(@RequestBody UserQuery userQuery) {
        Page<UserDTO> page = userService.page(userQuery);
        page.setList(page.getList().stream().peek(u -> {
            u.setPassword("");
            u.setSalt("");
            u.setOauthPwd("");
        }).collect(Collectors.toList()));
        return super.success(page);
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
