package com.ryanqy.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author tong.wu
 * created on 2023/12/13
 */
@Data
@NoArgsConstructor
public class UserRegisterRequest implements Serializable {

    @NotBlank
    private String username;

    @NotBlank
    private String name;

    @NotBlank
    private String password;

    @Positive
    private Integer age;

    @NotBlank
    private String telephone;

}
