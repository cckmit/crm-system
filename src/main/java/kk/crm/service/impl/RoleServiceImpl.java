package kk.crm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kk.crm.entity.Platform;
import kk.crm.entity.Role;
import kk.crm.mapper.PlatformMapper;
import kk.crm.mapper.RoleMapper;
import kk.crm.service.IPlatformService;
import kk.crm.service.IRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author maosi
 * @since 2021-05-08
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
