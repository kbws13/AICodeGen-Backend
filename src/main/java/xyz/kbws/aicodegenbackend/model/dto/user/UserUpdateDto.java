package xyz.kbws.aicodegenbackend.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @author kbws
 * @date 2025/9/27
 * @description:
 */
@Data
public class UserUpdateDto implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 用户昵称
     */
    private String name;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 简介
     */
    private String profile;

    /**
     * 用户角色：user/admin
     */
    private String role;

    private static final long serialVersionUID = 1L;
}
