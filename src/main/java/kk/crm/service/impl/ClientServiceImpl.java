package kk.crm.service.impl;

import kk.crm.entity.Client;
import kk.crm.mapper.ClientMapper;
import kk.crm.service.IClientService;
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
public class ClientServiceImpl extends ServiceImpl<ClientMapper, Client> implements IClientService {

}
