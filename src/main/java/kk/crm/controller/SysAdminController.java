package kk.crm.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kk.crm.entity.ResEntity;
import kk.crm.entity.Role;
import kk.crm.entity.SysAdmin;
import kk.crm.service.impl.RoleServiceImpl;
import kk.crm.service.impl.SysAdminServiceImpl;
import kk.crm.util.ResGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
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
@Api(tags = "用户接口")
public class SysAdminController {
    @Autowired
    SysAdminServiceImpl sysAdminService;

    @ApiOperation(value = "查询用户列表分页")
    @GetMapping("/admin")
    public ResEntity getPlatform(@RequestParam Integer page, @RequestParam Integer limit){
        Page iPage = new Page<>(page,limit,true);
        final Page list = sysAdminService.page(iPage);
        return ResGenerator.success(list);
    }

    @ApiOperation(value = "查询用户列表")
    @GetMapping("/adminall")
    public ResEntity getPlatform(){
        List<SysAdmin> list = sysAdminService.list();
        return ResGenerator.success(list);
    }

    @PostMapping("/admin")
    @ApiOperation(value = "增加用户")
    public ResEntity postPlatform(@RequestBody SysAdmin sysAdmin){
        sysAdmin.setId(UUID.randomUUID().toString());
        sysAdmin.setPwd(DigestUtils.md5DigestAsHex("123456".getBytes()));
        final boolean save = sysAdminService.save(sysAdmin);
        return ResGenerator.success(save);
    }

    @GetMapping("/searchadmin")
    @ApiOperation(value = "搜索用户信息")
    public ResEntity searchPlatform(@RequestParam(required = false) String realName){
        QueryWrapper<SysAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(realName!=null,"real_name",realName);
        final List<SysAdmin> list = sysAdminService.list(queryWrapper);
        return ResGenerator.success(list);
    }

    @DeleteMapping("/deletemanyadmin")
    @ApiOperation(value = "批量删除用户")
    public ResEntity deletePlatform(@RequestParam String ids){
        String[] split = ids.split(",");
        final ArrayList<String> strings = new ArrayList<>();
        for (String s : split) {
            strings.add(s);
        }
        final boolean remove = sysAdminService.removeByIds(strings);
        return ResGenerator.success(remove);
    }

    @PutMapping("/admin")
    @ApiOperation(value = "修改用户")
    public ResEntity putPlatform(@RequestBody SysAdmin sysAdmin){
        sysAdmin.setPwd(DigestUtils.md5DigestAsHex(sysAdmin.getPwd().getBytes()));
        final boolean save = sysAdminService.updateById(sysAdmin);
        return ResGenerator.success(save);
    }
}
