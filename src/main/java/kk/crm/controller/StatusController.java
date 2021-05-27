package kk.crm.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kk.crm.entity.ResEntity;
import kk.crm.entity.Status;
import kk.crm.service.impl.StatusServiceImpl;
import kk.crm.util.ResGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@Api(tags = "状态列表")
public class StatusController {

    @Autowired
    StatusServiceImpl statusService;

    @GetMapping("/statuslist")
    @ApiOperation(value = "状态列表")
    public ResEntity postOperator(){
        final List<Status> list = statusService.list();
        return ResGenerator.success(list);
    }
}
