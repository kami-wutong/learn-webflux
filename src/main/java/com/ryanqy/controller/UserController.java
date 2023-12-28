package com.ryanqy.controller;

import com.ryanqy.service.UserService;
import com.ryanqy.util.Result;
import com.ryanqy.vo.UserRegisterRequest;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author tong.wu
 * created on 2023/12/7
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping(value = "/register")
    public Result<Mono<Void>> register(@RequestBody UserRegisterRequest request) {
        return Result.success(userService.register(request));
    }

}

