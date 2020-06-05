package com.hollysys.tn.controller;


import com.hollysys.tn.common.controller.BaseController;
import com.hollysys.tn.entity.User;
import com.hollysys.tn.service.UserService;
import com.hollysys.tn.util.Response;
import org.springframework.web.bind.annotation.*;

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
    public Response login(@PathVariable("name") String code, @PathVariable("password") String password) {
        try {
            System.out.println("name：" + code + " ========= password：" + password);
            User user = userService.login(code, password);
            return new Response().success(user);
        }catch (Exception e) {
            return new Response().failure(e.getLocalizedMessage());
        }
    }

    @PostMapping("/register")
    public Response register(@RequestBody User user) {
        try {
            userService.userRegister(user);
            return new Response().success(user);
        }catch (Exception e) {
            return new Response().failure(e.getLocalizedMessage());
        }
    }
}
