package kk.crm.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kk.crm.entity.Job;
import kk.crm.entity.ResEntity;
import kk.crm.entity.Status;
import kk.crm.service.impl.JobServiceImpl;
import kk.crm.service.impl.StatusServiceImpl;
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
@Api(tags = "工作列表")
public class JobController {
    @Autowired
    JobServiceImpl jobService;

    @GetMapping("/joblist")
    @ApiOperation(value = "状态列表")
    public ResEntity postOperator(){
        final List<Job> list = jobService.list();
        return ResGenerator.success(list);
    }
}
