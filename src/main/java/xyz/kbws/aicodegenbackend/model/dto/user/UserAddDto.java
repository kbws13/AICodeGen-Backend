package xyz.kbws.aicodegenbackend.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @author kbws
 * @date 2025/9/27
 * @description:
 */
@Data
public class UserAddDto implements Serializable {

    /**
     * 用户昵称
     */
    private String name;

    /**
     * 账号
     */
    private String account;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户简介
     */
    private String profile;

    /**
     * 用户角色: user, admin
     */
    private String role;

    private static final long serialVersionUID = 1L;
}
