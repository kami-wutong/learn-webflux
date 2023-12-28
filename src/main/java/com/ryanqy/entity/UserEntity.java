package com.ryanqy.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author tong.wu
 * created on 2023/12/12
 */
@Data
@NoArgsConstructor
@Table(value = "tb_user")
public class UserEntity implements Serializable {

    @Id
    private Long id;

    /**
     * 用户登录账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 名称
     */
    private String name;

    /**
     * 电话号码
     */
    private String telephone;

    /**
     * 创建时间
     */
    @CreatedDate
    private LocalDateTime createdTime;

    /**
     * 记录创建人
     */
    @CreatedBy
    private Long createdBy;

    /**
     * 最近一次更新时间
     */
    @LastModifiedDate
    private LocalDateTime lastModifiedTime;

    /**
     * 最近一次更新人
     */
    @LastModifiedBy
    private Long lastModifiedBy;

}
