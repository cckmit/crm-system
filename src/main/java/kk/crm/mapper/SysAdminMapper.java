package kk.crm.mapper;

import kk.crm.entity.SysAdmin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import kk.crm.entity.SysRight;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author maosi
 * @since 2021-05-13
 */
@Mapper
public interface SysAdminMapper extends BaseMapper<SysAdmin> {
    List<SysRight> getRightList(String adminId);
}
