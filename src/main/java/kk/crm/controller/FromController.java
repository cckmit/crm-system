package kk.crm.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kk.crm.entity.From;
import kk.crm.entity.Job;
import kk.crm.entity.ResEntity;
import kk.crm.service.impl.FromServiceImpl;
import kk.crm.service.impl.JobServiceImpl;
import kk.crm.util.ResGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author maosi
 * @since 2021-05-24
 */
@RestController
@Api(tags = "来源列表")
public class FromController {
    @Autowired
    FromServiceImpl fromService;

    @GetMapping("/fromlist")
    @ApiOperation(value = "状态列表")
    public ResEntity postOperator(){
        final List<From> list = fromService.list();
        return ResGenerator.success(list);
    }
}
