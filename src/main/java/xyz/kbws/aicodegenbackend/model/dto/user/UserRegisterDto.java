package xyz.kbws.aicodegenbackend.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @author kbws
 * @date 2025/9/27
 * @description:
 */
@Data
public class UserRegisterDto implements Serializable {
    private static final long serialVersionUID = 3733607230436779028L;

    private String account;

    private String password;

    private String checkPassword;
}
