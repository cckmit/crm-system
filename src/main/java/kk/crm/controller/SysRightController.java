package kk.crm.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kk.crm.entity.*;
import kk.crm.service.impl.RoleRightServiceImpl;
import kk.crm.service.impl.SysAdminServiceImpl;
import kk.crm.service.impl.SysRightServiceImpl;
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
 * @since 2021-05-13
 */
@Api(tags = "权限接口")
@RestController
public class SysRightController {

    @Autowired
    SysRightServiceImpl sysRightService;

    @Autowired
    RoleRightServiceImpl roleRightService;

    @ApiOperation(value = "查询权限列表")
    @GetMapping("/right")
    public ResEntity getRight(){
        final List<SysRight> list = sysRightService.list();
        return ResGenerator.success(list);
    }

    @ApiOperation(value = "查询父级权限")
    @GetMapping("/rightparent")
    public ResEntity getRightMain(){
        final QueryWrapper<SysRight> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_name","");
        final List<SysRight> list = sysRightService.list(queryWrapper);
        return ResGenerator.success(list);
    }

    @ApiOperation(value = "根据父级id查子权限")
    @GetMapping("/rightson")
    public ResEntity getRightSon(@RequestParam String parentId){
        final QueryWrapper<SysRight> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",parentId);
        final List<SysRight> list = sysRightService.list(queryWrapper);
        return ResGenerator.success(list);
    }

    @PostMapping("/right")
    @ApiOperation(value = "增加权限")
    public ResEntity postRight(@RequestBody SysRight sysRight){
        sysRight.setId(UUID.randomUUID().toString());
        final boolean save = sysRightService.save(sysRight);
        return ResGenerator.success(save);
    }


    @DeleteMapping("/deletemanyright")
    @ApiOperation(value = "批量删除")
    public ResEntity deleteRight(@RequestParam String ids){
        String[] split = ids.split(",");
        final QueryWrapper<RoleRight> roleRightQueryWrapper = new QueryWrapper<>();
        final ArrayList<String> strings = new ArrayList<>();
        for (String s : split) {
            strings.add(s);
            roleRightQueryWrapper.eq("right_id",s);
            roleRightService.remove(roleRightQueryWrapper);
        }


        final boolean remove = sysRightService.removeByIds(strings);
        return ResGenerator.success(remove);
    }

    @PutMapping("/right")
    @ApiOperation(value = "修改权限")
    public ResEntity putRight(@RequestBody SysRight sysRight){
        final boolean save = sysRightService.updateById(sysRight);
        return ResGenerator.success(save);
    }
}
