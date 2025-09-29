package xyz.kbws.aicodegenbackend.model.dto.app;

import lombok.Data;

import java.io.Serializable;

/**
 * @author kbws
 * @date 2025/9/27
 * @description:
 */
@Data
public class AppAddDto implements Serializable {
    private static final long serialVersionUID = -3825300150703512741L;

    /**
     * 应用初始化的 prompt
     */
    private String initPrompt;
}
