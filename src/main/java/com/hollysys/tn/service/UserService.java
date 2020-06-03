package com.hollysys.tn.service;

import com.hollysys.tn.common.service.BaseService;
import com.hollysys.tn.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lushanyuan
 * @since 2020-05-28
 */
public interface UserService extends BaseService<User> {

    public User login( String name, String password ) throws Exception;

    public User userRegister( String name, String password ) throws Exception;

    public boolean verifyUser( User user ) throws RuntimeException;
}
