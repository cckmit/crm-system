package kk.crm.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import freemarker.template.utility.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kk.crm.entity.*;
import kk.crm.service.impl.RoleRightServiceImpl;
import kk.crm.service.impl.RoleServiceImpl;
import kk.crm.service.impl.SysRightServiceImpl;
import kk.crm.service.impl.SysRoleServiceImpl;
import kk.crm.util.ResGenerator;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
@Slf4j
@RestController
@Api(tags = "role和right的关联处理接口")
public class RoleRightController {

    @Autowired
    RoleRightServiceImpl roleRightService;

    @Autowired
    SysRightServiceImpl sysRightService;

    @Autowired
    RoleServiceImpl roleService;

    @ApiOperation(value = "查询role_right关联")
    @GetMapping("/roleright")
    public ResEntity getRoleRight(@RequestParam String roleId){
        final QueryWrapper<RoleRight> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",roleId);
        final List<RoleRight> list = roleRightService.list(queryWrapper);
        return ResGenerator.success(list);
    }

    @ApiOperation(value = "增加role_right关联")
    @PostMapping("/roleright")
    public ResEntity postRoleRight(@RequestParam String roleId,@RequestParam String rightId){
        try {
            ArrayList<String> list = new ArrayList<>();
            //清空改用户的所有权限
            final QueryWrapper<RoleRight> adminRoleQueryWrapper = new QueryWrapper<>();
            adminRoleQueryWrapper.eq("role_id",roleId);
            roleRightService.remove(adminRoleQueryWrapper);

            //设置权限
            Role role = new Role();
            role.setId(roleId);
            if (roleId.isEmpty()){
                role.setRightName("");
                roleService.updateById(role);
                return ResGenerator.success();
            }

            String[] split = rightId.split(","); //分割的是roleid
            for (String s : split) {
                RoleRight roleRight = new RoleRight();
                roleRight.setId(UUID.randomUUID().toString());
                roleRight.setRoleId(roleId);
                roleRight.setRightId(s);
                final boolean save = roleRightService.save(roleRight);

                final QueryWrapper<SysRight> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("id",s);
                SysRight right = sysRightService.getOne(queryWrapper);
                list.add(right.getRightName());
            }

            role.setRightName(StringUtils.join(list,","));
            roleService.updateById(role);
            return ResGenerator.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResGenerator.fail("添加失败");
        }
    }
}
