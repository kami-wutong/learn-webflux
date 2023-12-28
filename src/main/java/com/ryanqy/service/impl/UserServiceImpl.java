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
        return this.userRepository.getByUsername(request.getUsername())
                .flatMap(existingUser -> Mono.<UserEntity>error(CommonUtils.newServiceException("username already exists.")))
                .switchIfEmpty(Mono.defer(() -> {
                    UserEntity userEntity = userConverter.convertToUserEntity(request);
                    userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

                    log.info("Starting to save user: {}", userEntity.getUsername());
                    return userRepository.save(userEntity);
                })).then();
    }

    @Override
    public Mono<UserEntity> findByUsername(String username) {
        return this.userRepository.getByUsername(username);
    }

}
