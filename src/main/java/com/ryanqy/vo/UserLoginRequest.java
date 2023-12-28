package com.ryanqy.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author tong.wu
 * created on 2023/12/27
 */
@Data
@NoArgsConstructor
public class UserLoginRequest implements Serializable {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
