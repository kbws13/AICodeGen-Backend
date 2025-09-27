package xyz.kbws.aicodegenbackend.ai;

import cn.hutool.json.JSONUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import xyz.kbws.aicodegenbackend.ai.model.HtmlCodeResult;
import xyz.kbws.aicodegenbackend.ai.model.MultiFileCodeResult;
import xyz.kbws.aicodegenbackend.core.AiCodeGeneratorFacade;
import xyz.kbws.aicodegenbackend.model.enums.CodeGenTypeEnum;

import java.util.List;

/**
 * @author kbws
 * @date 2025/9/27
 * @description:
 */
@Slf4j
@SpringBootTest
public class AiCodeGeneratorServiceTest {
    
    @Resource
    private AiCodeGeneratorService aiCodeGeneratorService;
    
    @Resource
    private AiCodeGeneratorFacade aiCodeGeneratorFacade;

    @Test
    void generateHtmlCode() {
        HtmlCodeResult result = aiCodeGeneratorService.generateHtmlCode("做个工作记录小工具");
        log.info(JSONUtil.toJsonStr(result));
    }

    @Test
    void generateMultiFileCode() {
        MultiFileCodeResult multiFileCode = aiCodeGeneratorService.generateMultiFileCode("做个留言板");
        Assertions.assertNotNull(multiFileCode);
    }

    @Test
    void generateAndSaveCodeStream() {
        Flux<String> codeStream = aiCodeGeneratorFacade.generateAndSaveCodeStream("任务记录网站", CodeGenTypeEnum.MULTI_FILE);
        // 阻塞等待所有数据收集完成
        List<String> result = codeStream.collectList().block();
        // 验证结果
        Assertions.assertNotNull(result);
        String completeContent = String.join("", result);
        Assertions.assertNotNull(completeContent);
    }


}
