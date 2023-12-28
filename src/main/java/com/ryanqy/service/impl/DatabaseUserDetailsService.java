package com.ryanqy.service.impl;

import com.ryanqy.entity.UserEntity;
import com.ryanqy.service.UserService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Collections;

/**
 * @author tong.wu
 * created on 2023/12/27
 */
@Service
public class DatabaseUserDetailsService implements ReactiveUserDetailsService {

    @Resource
    private UserService userService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        UserEntity defaultUserEntity = new UserEntity();
        defaultUserEntity.setUsername(username);
        defaultUserEntity.setPassword(passwordEncoder.encode(username));

        if (StringUtils.isEmpty(username)) {
            return Mono.empty();
        }

        return userService.findByUsername(username)
                .defaultIfEmpty(defaultUserEntity)
                .map(it -> new User(it.getUsername(), it.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("admin"))));
    }

}
