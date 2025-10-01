package xyz.kbws.aicodegenbackend.ai;

import dev.langchain4j.service.SystemMessage;
import xyz.kbws.aicodegenbackend.model.enums.CodeGenTypeEnum;

/**
 * @author kbws
 * @date 2025/10/1
 * @description:
 */
public interface AiCodeGenTypeRoutingService {
    /**
     * 根据用户需求智能选择代码生成类型
     *
     * @param userPrompt 用户输入的需求描述
     * @return 推荐的代码生成类型
     */
    @SystemMessage(fromResource = "prompt/codegen-routing-system-prompt.txt")
    CodeGenTypeEnum routeCodeGenType(String userPrompt);
}
