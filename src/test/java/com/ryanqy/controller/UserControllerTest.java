package com.ryanqy.controller;

import com.ryanqy.util.Result;
import com.ryanqy.util.ResultCodeEnum;
import com.ryanqy.vo.UserRegisterRequest;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.simple.RandomSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.testcontainers.shaded.org.apache.commons.lang3.RandomStringUtils;

/**
 * @author tong.wu
 * created on 2023/12/18
 */
@Slf4j
@SpringBootTest
@AutoConfigureWebTestClient
class UserControllerTest {

    @Resource
    private WebTestClient webTestClient;

    @Test
    void register() {
        UniformRandomProvider randomProvider = RandomSource.XO_RO_SHI_RO_128_PP.create();

        String username = "junit-" + RandomStringUtils.randomAlphabetic(8);

        UserRegisterRequest request = new UserRegisterRequest();
        request.setUsername(username);
        request.setPassword("12345678");
        request.setName(RandomStringUtils.randomAlphabetic(8));
        request.setAge(randomProvider.nextInt(19, 34));
        request.setTelephone("17688193995");

        EntityExchangeResult<Result<Void>> resultEntityExchangeResult = webTestClient.post()
                .uri("/user/register").contentType(MediaType.APPLICATION_JSON).bodyValue(request)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(new ParameterizedTypeReference<Result<Void>>() {

                })
                .returnResult();

        Result<Void> result = resultEntityExchangeResult.getResponseBody();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(ResultCodeEnum.OK.getCode(), result.getCode());

        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("username", username);
        map.add("password", "12345678");
        webTestClient.post()
                .uri("/user/login").contentType(MediaType.APPLICATION_FORM_URLENCODED).bodyValue(map)
                .exchange()
                .expectStatus().is2xxSuccessful();

    }

}