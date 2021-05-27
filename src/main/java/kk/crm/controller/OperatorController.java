package kk.crm.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kk.crm.entity.Operator;
import kk.crm.entity.ResEntity;
import kk.crm.entity.Trace;
import kk.crm.service.impl.OperatorServiceImpl;
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
@Api(tags = "移交操作接口")
public class OperatorController {
    @Autowired
    OperatorServiceImpl operatorService;

    @PostMapping("/operator")
    @ApiOperation(value = "添加移交接口")
    public ResEntity postOperator(@RequestBody Operator operator){
        try {
            operatorService.save(operator);
            return ResGenerator.success("添加成功");
        }catch (Exception e){
            return ResGenerator.fail("添加失败");
        }
    }


    @ApiOperation(value = "移交列表")
    @GetMapping("/operator")
    public ResEntity getTrace(@RequestParam Integer page, @RequestParam Integer limit){
        Page iPage = new Page<>(page,limit,true);
        final Page list = operatorService.page(iPage);
        return ResGenerator.success(list);
    }
}
