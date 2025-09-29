package xyz.kbws.aicodegenbackend.model.dto.app;

import lombok.Data;
import xyz.kbws.aicodegenbackend.common.PageRequest;

import java.io.Serializable;

/**
 * @author kbws
 * @date 2025/9/27
 * @description:
 */
@Data
public class AppQueryDto extends PageRequest implements Serializable {
    private static final long serialVersionUID = -3034671098126197649L;

    /**
     * id
     */
    private Long id;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 应用封面
     */
    private String cover;

    /**
     * 应用初始化的 prompt
     */
    private String initPrompt;

    /**
     * 代码生成类型（枚举）
     */
    private String codeGenType;

    /**
     * 部署标识
     */
    private String deployKey;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 创建用户id
     */
    private Long userId;
}
