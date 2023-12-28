package com.ryanqy.service.impl;

import com.ryanqy.entity.UserEntity;
import com.ryanqy.repository.UserRepository;
import com.ryanqy.service.UserService;
import com.ryanqy.util.CommonUtils;
import com.ryanqy.util.converter.UserConverter;
import com.ryanqy.vo.UserRegisterRequest;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author tong.wu
 * created on 2023/12/7
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private UserConverter userConverter;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public Mono<Void> register(UserRegisterRequest request) {
        Mono<UserEntity> userEntityMono = Mono.just(request)
                .map(r -> userConverter.convertToUserEntity(request))
                .doOnNext(r -> r.setPassword(passwordEncoder.encode(r.getPassword())))
                .flatMap(userRepository::save);

        return this.userRepository.getByUsername(request.getUsername())
                .flatMap(existingUser -> Mono.error(CommonUtils.newServiceException("username already exists.")))
                .switchIfEmpty(userEntityMono)
                .then();
    }

    @Override
    public Mono<UserEntity> findByUsername(String username) {
        return this.userRepository.getByUsername(username);
    }

}
