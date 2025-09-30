package xyz.kbws.aicodegenbackend.controller;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import xyz.kbws.aicodegenbackend.annotation.AuthCheck;
import xyz.kbws.aicodegenbackend.common.BaseResponse;
import xyz.kbws.aicodegenbackend.common.ResultUtil;
import xyz.kbws.aicodegenbackend.constant.UserConstant;
import xyz.kbws.aicodegenbackend.exception.ErrorCode;
import xyz.kbws.aicodegenbackend.exception.ThrowUtil;
import xyz.kbws.aicodegenbackend.model.dto.chatHistory.ChatHistoryQueryDto;
import xyz.kbws.aicodegenbackend.model.entity.ChatHistory;
import xyz.kbws.aicodegenbackend.model.entity.User;
import xyz.kbws.aicodegenbackend.service.ChatHistoryService;
import xyz.kbws.aicodegenbackend.service.UserService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 对话历史表 控制层。
 *
 * @author <a href="https://github.com/kbws13">空白无上</a>
 */
@RestController
@RequestMapping("/chatHistory")
public class ChatHistoryController {

    @Resource
    private ChatHistoryService chatHistoryService;

    @Resource
    private UserService userService;

    /**
     * 分页查询某个应用的对话历史（游标查询）
     *
     * @param appId          应用ID
     * @param pageSize       页面大小
     * @param lastCreateTime 最后一条记录的创建时间
     * @param request        请求
     * @return 对话历史分页
     */
    @GetMapping("/app/{appId}")
    public BaseResponse<Page<ChatHistory>> listAppChatHistory(@PathVariable Long appId,
                                                              @RequestParam(defaultValue = "10") int pageSize,
                                                              @RequestParam(required = false) LocalDateTime lastCreateTime,
                                                              HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        Page<ChatHistory> result = chatHistoryService.listAppChatHistoryByPage(appId, pageSize, lastCreateTime, loginUser);
        return ResultUtil.success(result);
    }

    /**
     * 管理员分页查询所有对话历史
     *
     * @param chatHistoryQueryDto 查询请求
     * @return 对话历史分页
     */
    @PostMapping("/admin/list/page/vo")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Page<ChatHistory>> listAllChatHistoryByPageForAdmin(@RequestBody ChatHistoryQueryDto chatHistoryQueryDto) {
        ThrowUtil.throwIf(chatHistoryQueryDto == null, ErrorCode.PARAMS_ERROR);
        long pageNum = chatHistoryQueryDto.getPageNum();
        long pageSize = chatHistoryQueryDto.getPageSize();
        // 查询数据
        QueryWrapper queryWrapper = chatHistoryService.getQueryWrapper(chatHistoryQueryDto);
        Page<ChatHistory> result = chatHistoryService.page(Page.of(pageNum, pageSize), queryWrapper);
        return ResultUtil.success(result);
    }


}
