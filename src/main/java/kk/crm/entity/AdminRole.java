package kk.crm.entity;

import java.io.Serializable;
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
public class AdminRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String adminId;

    private String roleId;


}
