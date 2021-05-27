package kk.crm.service.impl;

import kk.crm.entity.SysAdmin;
import kk.crm.entity.SysRight;
import kk.crm.mapper.SysAdminMapper;
import kk.crm.service.ISysAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author maosi
 * @since 2021-05-13
 */
@Service
public class SysAdminServiceImpl extends ServiceImpl<SysAdminMapper, SysAdmin> implements ISysAdminService {

    @Autowired
    SysAdminMapper adminMapper;

    @Override
    public List<SysRight> getRightList(String adminId) {
        return adminMapper.getRightList(adminId);
    }
}
