package xyz.kbws.aicodegenbackend.model.dto.app;

import lombok.Data;

import java.io.Serializable;

/**
 * @author kbws
 * @date 2025/9/27
 * @description:
 */
@Data
public class AppDeployDto implements Serializable {
    private static final long serialVersionUID = 4770463364697851129L;

    /**
     * 应用 id
     */
    private Long appId;
}
