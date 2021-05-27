package kk.crm.service;

import kk.crm.entity.SysAdmin;
import com.baomidou.mybatisplus.extension.service.IService;
import kk.crm.entity.SysRight;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author maosi
 * @since 2021-05-13
 */
public interface ISysAdminService extends IService<SysAdmin> {
    List<SysRight> getRightList(String adminId);
}
