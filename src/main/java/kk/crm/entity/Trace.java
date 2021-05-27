package kk.crm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author maosi
 * @since 2021-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Trace implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Date traceTime;

    private String traceDetails;

    private String traceType;

    /**
     * 3 优 2中  1差
     */
    private String traceResult;

    /**
     * 客户id
     */
    private Integer clientId;

    /**
     * 自动填入当前登录用户，用户员工对象是	不可更改
     */
    private String inputUser;

    private String type;


}
