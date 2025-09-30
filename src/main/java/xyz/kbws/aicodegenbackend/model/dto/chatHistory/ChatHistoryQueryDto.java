package xyz.kbws.aicodegenbackend.model.dto.chatHistory;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.kbws.aicodegenbackend.common.PageRequest;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author kbws
 * @date 2025/9/30
 * @description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ChatHistoryQueryDto extends PageRequest implements Serializable {
    private static final long serialVersionUID = -7043626477225331042L;

    private Long id;

    private String message;

    private String messageType;

    private Long appId;

    private Long userId;

    /**
     * 游标查询
     */
    private LocalDateTime lastCreateTime;
}
