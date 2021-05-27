package kk.crm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kk.crm.entity.*;
import kk.crm.service.impl.RoleServiceImpl;
import kk.crm.service.impl.SysAdminServiceImpl;
import kk.crm.util.JWTUtil;
import kk.crm.util.ResGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@Api(tags = "登录接口")
public class LoginController {

    @Autowired
    RoleServiceImpl roleService;

    @Autowired
    SysAdminServiceImpl adminService;

    //登录
    @PostMapping("/login")
    @ApiOperation("登录")
    public ResEntity getPlatform(@RequestBody Login login){

        //账号密码是否正确
        QueryWrapper<SysAdmin> adminQueryWrapper = new QueryWrapper<>();
        adminQueryWrapper.eq("admin_code", login.getUsername());
        SysAdmin admin = adminService.getOne(adminQueryWrapper);
        if (admin==null){
            return new ResEntity().setStatus(420).setMsg("登录失败,账号或密码错误");
        }
        if (!admin.getPwd().equals(DigestUtils.md5DigestAsHex(login.getPassword().getBytes()))){
            return new ResEntity().setStatus(420).setMsg("登录失败,账号或密码错误");
        }

        //登录成功修改登陆时间
        UpdateWrapper<SysAdmin> adminUpdateWrapper = new UpdateWrapper<>();
        adminUpdateWrapper.eq("admin_code",login.getUsername());
        adminUpdateWrapper.set("login_time",new Date());
        adminService.update(adminUpdateWrapper);

        //加载用户权限
        HashMap<String, Object> map = new HashMap<>();
        map.put("role",admin.getRoleName());
        //设置用户权限
        final String token = JWTUtil.createToken(map);
        //返回体
        HashMap<String, Object> res = new HashMap<>();
        res.put("token",token);
        //返回权限具体内容
        final List<SysRight> rightList = adminService.getRightList(admin.getId());
        System.out.println(rightList);
        res.put("right",rightList);
        //返回用户信息
        admin.setPwd("");
        res.put("user",admin);
        return ResGenerator.success(res);
    }

    //修改密码
}
