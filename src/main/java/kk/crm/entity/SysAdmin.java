package kk.crm.entity;

import java.time.LocalDateTime;
import java.sql.Blob;
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
 * @since 2021-05-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysAdmin implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String adminCode;

    private String pwd;

    private String realName;

    private Integer sex;

    private String phone;

    private Date loginTime;

    private String roleName;


}
