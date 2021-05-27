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
 * @since 2021-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Status implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String status;


}
