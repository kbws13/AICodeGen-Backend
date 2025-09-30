package xyz.kbws.aicodegenbackend.service;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import xyz.kbws.aicodegenbackend.model.dto.chatHistory.ChatHistoryQueryDto;
import xyz.kbws.aicodegenbackend.model.entity.ChatHistory;
import xyz.kbws.aicodegenbackend.model.entity.User;

import java.time.LocalDateTime;

/**
 * 对话历史表 服务层。
 *
 * @author <a href="https://github.com/kbws13">空白无上</a>
 */
public interface ChatHistoryService extends IService<ChatHistory> {
    Boolean addChatMessage(Long appId, String message, String messageType, Long userId);

    Boolean deleteByAppId(Long appId);

    Page<ChatHistory> listAppChatHistoryByPage(Long appId, int pageSize,
                                               LocalDateTime lastCreateTime,
                                               User loginUser);

    QueryWrapper getQueryWrapper(ChatHistoryQueryDto chatHistoryQueryDto);

    Integer loadChatHistoryToMemory(Long appId, MessageWindowChatMemory chatMemory, int maxCount);
}
