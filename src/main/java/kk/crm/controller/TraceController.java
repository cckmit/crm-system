package kk.crm.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kk.crm.entity.ResEntity;
import kk.crm.entity.Trace;
import kk.crm.service.impl.TraceServiceImpl;
import kk.crm.util.ResGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author maosi
 * @since 2021-05-24
 */
@RestController
@Api(tags = "跟进接口")
public class TraceController {
    @Autowired
    TraceServiceImpl traceService;
    @ApiOperation(value = "查询跟进列表")
    @GetMapping("/trace")
    public ResEntity getTrace(@RequestParam Integer page, @RequestParam Integer limit){
        Page iPage = new Page<>(page,limit,true);
        final Page list = traceService.page(iPage);
        return ResGenerator.success(list);
    }

    @ApiOperation(value = "修改跟进列表")
    @PutMapping("/trace")
    public ResEntity putTrace(@RequestBody Trace trace){
        try {
            traceService.updateById(trace);
            return ResGenerator.success("修改成功");
        }catch (Exception e){
            return ResGenerator.fail("修改失败");
        }
    }

    @ApiOperation(value = "添加跟进列表")
    @PostMapping("/trace")
    public ResEntity postTrace(@RequestBody Trace trace){
        try {
            traceService.save(trace);
            return ResGenerator.success("添加成功");
        }catch (Exception e){
            return ResGenerator.fail("添加失败");
        }
    }
}
