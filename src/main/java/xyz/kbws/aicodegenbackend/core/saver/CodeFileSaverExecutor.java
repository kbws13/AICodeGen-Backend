package xyz.kbws.aicodegenbackend.core.saver;

import xyz.kbws.aicodegenbackend.ai.model.HtmlCodeResult;
import xyz.kbws.aicodegenbackend.ai.model.MultiFileCodeResult;
import xyz.kbws.aicodegenbackend.exception.BusinessException;
import xyz.kbws.aicodegenbackend.exception.ErrorCode;
import xyz.kbws.aicodegenbackend.model.enums.CodeGenTypeEnum;

import java.io.File;

/**
 * @author kbws
 * @date 2025/9/27
 * @description:
 */
public class CodeFileSaverExecutor {
    private static final HtmlCodeFileSaverTemplate htmlCodeFileSaver = new HtmlCodeFileSaverTemplate();

    private static final MultiFileCodeFileSaverTemplate multiFileCodeFileSaver = new MultiFileCodeFileSaverTemplate();

    /**
     * 执行代码保存
     *
     * @param codeResult  代码结果对象
     * @param codeGenType 代码生成类型
     * @param appId 应用 ID
     * @return 保存的目录
     */
    public static File executeSaver(Object codeResult, CodeGenTypeEnum codeGenType, Long appId) {
        return switch (codeGenType) {
            case HTML -> htmlCodeFileSaver.saveCode((HtmlCodeResult) codeResult, appId);
            case MULTI_FILE -> multiFileCodeFileSaver.saveCode((MultiFileCodeResult) codeResult, appId);
            default -> throw new BusinessException(ErrorCode.SYSTEM_ERROR, "不支持的代码生成类型: " + codeGenType);
        };
    }
}
