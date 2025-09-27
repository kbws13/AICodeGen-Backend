package xyz.kbws.aicodegenbackend.model.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.kbws.aicodegenbackend.common.PageRequest;

import java.io.Serializable;

/**
 * @author kbws
 * @date 2025/9/27
 * @description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserQueryDto extends PageRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 用户昵称
     */
    private String name;

    /**
     * 账号
     */
    private String account;

    /**
     * 简介
     */
    private String profile;

    /**
     * 用户角色：user/admin/ban
     */
    private String role;

    private static final long serialVersionUID = 1L;
}

