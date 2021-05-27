package kk.crm.service.impl;

import kk.crm.entity.Job;
import kk.crm.mapper.JobMapper;
import kk.crm.service.IJobService;
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
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements IJobService {

}
