package xyz.kbws.aicodegenbackend.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author kbws
 * @date 2025/9/27
 * @description:
 */
@Data
public class DeleteRequest implements Serializable {
    
    private Long id;

    private static final long serialVersionUID = 1L;
}

