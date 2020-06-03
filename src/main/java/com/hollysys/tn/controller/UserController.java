package com.hollysys.tn.controller;


import com.hollysys.tn.common.controller.BaseController;
import com.hollysys.tn.entity.User;
import com.hollysys.tn.service.UserService;
import com.hollysys.tn.util.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lushanyuan
 * @since 2020-05-28
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    @GetMapping("/login/{name}/{password}")
    public Response login(@PathVariable("name") String name, @PathVariable("password") String password) {
        try {
            User user = userService.login(name, password);
            return new Response().success(user);
        }catch (Exception e) {
            return new Response().failure(e.getLocalizedMessage());
        }
    }

    @GetMapping("/register/{name}/password")
    public Response register(@PathVariable("name") String name, @PathVariable("password") String password) {
        try {
            User user = userService.userRegister(name, password);
            return new Response().success(user);
        }catch (Exception e) {
            return new Response().failure(e.getLocalizedMessage());
        }
    }
}
