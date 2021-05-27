package kk.crm.service.impl;

import kk.crm.entity.Status;
import kk.crm.mapper.StatusMapper;
import kk.crm.service.IStatusService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author maosi
 * @since 2021-05-24
 */
@Service
public class StatusServiceImpl extends ServiceImpl<StatusMapper, Status> implements IStatusService {

}
