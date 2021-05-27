package kk.crm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kk.crm.entity.Platform;
import kk.crm.entity.ResEntity;
import kk.crm.util.ResGenerator;
import kk.crm.service.impl.PlatformServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
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
@Api(tags = "平台接口")
@RestController
public class PlatformController {
    @Autowired
    PlatformServiceImpl platformService;

    @ApiOperation(value = "查询平台列表")
    @GetMapping("/platform")
//    @PreAuthorize("hasAuthority('user')")
    public ResEntity getPlatform(@RequestParam Integer page, @RequestParam Integer limit){
        Page iPage = new Page<>(page,limit,true);
        final Page list = platformService.page(iPage);
        return ResGenerator.success(list);
    }

    @PostMapping("/platform")
    @ApiOperation(value = "增加平台")
    public ResEntity postPlatform(@RequestBody Platform platform){
        platform.setId(UUID.randomUUID().toString());
        final boolean save = platformService.save(platform);
        return ResGenerator.success(save);
    }

    @GetMapping("/search")
    @ApiOperation(value = "搜索平台信息")
    public ResEntity searchPlatform(@RequestParam(required = false) String platformName,@RequestParam(required = false) String platformIntro){
        QueryWrapper<Platform> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(platformName!=null,"platform_name",platformName);
        queryWrapper.like(platformIntro!=null,"platform_intro",platformIntro);
        final List<Platform> list = platformService.list(queryWrapper);
        return ResGenerator.success(list);
    }

    @DeleteMapping("/deletemany")
    @ApiOperation(value = "批量删除")
    public ResEntity deletePlatform(@RequestParam String ids){
        String[] split = ids.split(",");
        final ArrayList<String> strings = new ArrayList<>();
        for (String s : split) {
            strings.add(s);
        }
        final boolean remove = platformService.removeByIds(strings);
        return ResGenerator.success(remove);
    }

    @PutMapping("/platform")
    @ApiOperation(value = "修改平台")
    public ResEntity putPlatform(@RequestBody Platform platform){
        final boolean save = platformService.updateById(platform);
        return ResGenerator.success(save);
    }
}
