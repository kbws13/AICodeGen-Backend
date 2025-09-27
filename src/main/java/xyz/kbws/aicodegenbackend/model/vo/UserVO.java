package xyz.kbws.aicodegenbackend.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author kbws
 * @date 2025/9/27
 * @description:
 */
@Data
public class UserVO implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 账号
     */
    private String account;

    /**
     * 用户昵称
     */
    private String name;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户简介
     */
    private String profile;

    /**
     * 用户角色：user/admin
     */
    private String role;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    private static final long serialVersionUID = 1L;
}
