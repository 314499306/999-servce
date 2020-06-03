package com.hollysys.tn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hollysys.tn.common.service.BaseCRUDService;
import com.hollysys.tn.entity.User;
import com.hollysys.tn.mapper.UserMapper;
import com.hollysys.tn.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lushanyuan
 * @since 2020-05-28
 */
@Service
public class UserServiceImpl extends BaseCRUDService<UserMapper, User> implements UserService{

    @Resource
    private UserMapper userMapper;

    @Resource
    private BCryptPasswordEncoder encoder;

    @Override
    public User login(String name, String password) throws Exception{
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.eq("name", name);
        User userLogin = userMapper.selectOne(wrapper);
        if (userLogin != null && encoder.matches(password, userLogin.getPassword())) {
            return userLogin;
        } else {
            throw new Exception("用户名或密码错误");
        }
    }

    @Override
    public User userRegister(String name, String password) throws Exception {
        User user = new User();
        user.setName(name);
        user.setPassword(encoder.encode(password));
        int i = userMapper.insert(user);
        if (i == 1){
            return user;
        }else {
            throw new Exception("注册失败");
        }
    }

    @Override
    public boolean verifyUser(User user) throws RuntimeException {
        return false;
    }
}
