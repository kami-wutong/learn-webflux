package com.ryanqy.service;

import com.ryanqy.entity.UserEntity;
import com.ryanqy.vo.UserLoginRequest;
import com.ryanqy.vo.UserRegisterRequest;
import org.springframework.security.core.userdetails.User;
import reactor.core.publisher.Mono;

/**
 * @author tong.wu
 * created on 2023/12/7
 */
public interface UserService {

    Mono<Void> register(UserRegisterRequest request);

    Mono<UserEntity> findByUsername(String username);
}

