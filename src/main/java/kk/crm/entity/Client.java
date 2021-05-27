package kk.crm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value="Client对象", description="")
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer age;

    private String qq;

    private String phone;

    private String wechat;

    private Integer gender;

    private String descr;

    private String source;

    @ApiModelProperty(value = "承接人")
    private String seller;

    private String job;

    private String status;

    @ApiModelProperty(value = "创建人")
    @TableField("inputUser")
    private String inputuser;

    @ApiModelProperty(value = "转正时间")
    @TableField("positiveTime")
    private LocalDateTime positivetime;


}
