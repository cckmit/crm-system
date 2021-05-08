package kk.crm.controller;


import kk.crm.entity.Platform;
import kk.crm.entity.ResEntity;
import kk.crm.util.ResGenerator;
import kk.crm.service.impl.PlatformServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author maosi
 * @since 2021-05-08
 */
@RestController
public class PlatformController {
    @Autowired
    PlatformServiceImpl platformService;

    @GetMapping("/api/platform")
    @PreAuthorize("hasAuthority('user')")
    public ResEntity getPlatform(){
        final List<Platform> list = platformService.list();
        return ResGenerator.success(list);
    }
}
