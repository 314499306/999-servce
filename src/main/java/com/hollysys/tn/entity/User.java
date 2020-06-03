package com.hollysys.tn.entity;

import com.hollysys.tn.common.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lushanyuan
 * @since 2020-05-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class User extends AbstractEntity {

    private String code;

    private String email;

    private String name;

    private String nickName;

    private String password;

    private String regTime;


}
