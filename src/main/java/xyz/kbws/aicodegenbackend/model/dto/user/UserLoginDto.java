package xyz.kbws.aicodegenbackend.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @author kbws
 * @date 2025/9/27
 * @description:
 */
@Data
public class UserLoginDto implements Serializable {
    private static final long serialVersionUID = -7735339184557564537L;
    
    private String account;
    
    private String password;
}
