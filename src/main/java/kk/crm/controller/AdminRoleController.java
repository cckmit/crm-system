package kk.crm.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kk.crm.entity.*;
import kk.crm.service.impl.AdminRoleServiceImpl;
import kk.crm.service.impl.RoleServiceImpl;
import kk.crm.service.impl.SysAdminServiceImpl;
import kk.crm.service.impl.SysRoleServiceImpl;
import kk.crm.util.ResGenerator;
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
@RestController
@Api(tags = "admin和role的关联处理接口")
public class AdminRoleController {

    @Autowired
    AdminRoleServiceImpl adminRoleService;

    @Autowired
    SysAdminServiceImpl sysAdminService;

    @Autowired
    RoleServiceImpl RoleService;

    @ApiOperation(value = "查询admin_role关联")
    @GetMapping("/adminrole")
    public ResEntity getAdminRole(@RequestParam String adminId){
        final QueryWrapper<AdminRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("admin_id",adminId);
        final List<AdminRole> list = adminRoleService.list(queryWrapper);
        return ResGenerator.success(list);
    }

    @ApiOperation(value = "增加admin_role关联")
    @PostMapping("/adminrole")
    public ResEntity postAdminRole(@RequestParam String adminId,@RequestParam String roleId){
        try {
            ArrayList<String> list = new ArrayList<>();
            //清空改用户的所有权限
            final QueryWrapper<AdminRole> adminRoleQueryWrapper = new QueryWrapper<>();
            adminRoleQueryWrapper.eq("admin_id",adminId);
            final boolean remove = adminRoleService.remove(adminRoleQueryWrapper);

            //设置权限
            SysAdmin sysAdmin = new SysAdmin();
            sysAdmin.setId(adminId);
            if (roleId.isEmpty()){
                sysAdmin.setRoleName("");
                sysAdminService.updateById(sysAdmin);
                return ResGenerator.success();
            }
            String[] split = roleId.split(","); //分割的是roleid
            for (String s : split) {
                AdminRole adminRole = new AdminRole();
                adminRole.setId(UUID.randomUUID().toString());
                adminRole.setAdminId(adminId);
                adminRole.setRoleId(s);
                final boolean save = adminRoleService.save(adminRole);

                //查询角色
                QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("id",s);
                Role role = RoleService.getOne(queryWrapper);
                list.add(role.getRoleName());
            }


            sysAdmin.setRoleName(StringUtils.join(list,","));
            final boolean update = sysAdminService.updateById(sysAdmin);
            return ResGenerator.success(update);
        }catch (Exception e){
            return ResGenerator.fail("添加失败");
        }
    }

}
