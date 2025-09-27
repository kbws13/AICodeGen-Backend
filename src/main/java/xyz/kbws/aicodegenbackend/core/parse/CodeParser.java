package xyz.kbws.aicodegenbackend.core.parse;


/**
 * @author kbws
 * @date 2025/9/27
 * @description: 提供静态方法解析不同类型的代码内容
 */
public interface CodeParser<T> {

    /**
     * 解析代码内容
     *
     * @param codeContent 原始代码内容
     * @return 解析后的结果对象
     */
    T parseCode(String codeContent);
}
