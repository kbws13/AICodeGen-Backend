package xyz.kbws.aicodegenbackend.model.dto.app;

import lombok.Data;

import java.io.Serializable;

/**
 * @author kbws
 * @date 2025/9/27
 * @description:
 */
@Data
public class AppUpdateDto implements Serializable {
    private static final long serialVersionUID = -9036350345289110829L;

    /**
     * id
     */
    private Long id;

    /**
     * 应用名称
     */
    private String appName;
    
    private Integer priority;
}
