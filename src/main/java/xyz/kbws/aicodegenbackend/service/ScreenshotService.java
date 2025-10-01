package xyz.kbws.aicodegenbackend.service;

/**
 * @author kbws
 * @date 2025/10/1
 * @description:
 */
public interface ScreenshotService {
    /**
     * 通用的截图服务，可以得到访问地址
     *
     * @param webUrl 网址
     * @return
     */
    String generateAndUploadScreenshot(String webUrl);
}
