package xyz.kbws.aicodegenbackend.service;

import jakarta.servlet.http.HttpServletResponse;

/**
 * @author kbws
 * @date 2025/10/1
 * @description:
 */
public interface ProjectDownloadService {
    /**
     * 下载项目为压缩包
     *
     * @param projectPath
     * @param downloadFileName
     * @param response
     */
    void downloadProjectAsZip(String projectPath, String downloadFileName, HttpServletResponse response);
}
