package kk.crm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kk.crm.entity.Platform;
import kk.crm.entity.ResEntity;
import kk.crm.entity.Role;
import kk.crm.service.impl.PlatformServiceImpl;
import kk.crm.service.impl.RoleServiceImpl;
import kk.crm.util.ResGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author maosi
 * @since 2021-05-08
 */
@Api(tags = "角色接口")
@RestController
public class RoleController {
    @Autowired
    RoleServiceImpl roleService;

    @ApiOperation(value = "查询角色列表")
    @GetMapping("/role")
//    @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer limit
    public ResEntity getRole(){
        final List<Role> list = roleService.list();
        return ResGenerator.success(list);
//        if (page == null){
//            final List<Role> list = roleService.list();
//            return ResGenerator.success(list);
//        }else {
//            Page iPage = new Page<>(page,limit,true);
//            final Page list = roleService.page(iPage);
//            return ResGenerator.success(list);
//        }
    }

    @ApiOperation(value = "查询单个角色")
    @GetMapping("/roleone")
    public ResEntity getRoleOne(@RequestParam String roleId){
        final Role role = roleService.getById(roleId);
        return ResGenerator.success(role);
    }

    @PostMapping("/role")
    @ApiOperation(value = "增加角色")
    public ResEntity postRole(@RequestBody Role role){
        role.setId(UUID.randomUUID().toString());
        final boolean save = roleService.save(role);
        return ResGenerator.success(save);
    }

    @GetMapping("/searchrole")
    @ApiOperation(value = "搜索角色信息")
    public ResEntity searchRole(@RequestParam(required = false) String roleName,@RequestParam(required = false) String rightName){
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(roleName!=null,"role_name",roleName);
        queryWrapper.like(rightName!=null,"role_name",rightName);
        final List<Role> list = roleService.list(queryWrapper);
        return ResGenerator.success(list);
    }

    @DeleteMapping("/deletemanyrole")
    @ApiOperation(value = "批量删除")
    public ResEntity deleteRole(@RequestParam String ids){
        String[] split = ids.split(",");
        final ArrayList<String> strings = new ArrayList<>();
        for (String s : split) {
            strings.add(s);
        }
        final boolean remove = roleService.removeByIds(strings);
        return ResGenerator.success(remove);
    }

    @PutMapping("/role")
    @ApiOperation(value = "修改角色")
    public ResEntity putRole(@RequestBody Role role){
        final boolean save = roleService.updateById(role);
        return ResGenerator.success(save);
    }
}
