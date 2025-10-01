package xyz.kbws.aicodegenbackend.config;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author kbws
 * @date 2025/10/1
 * @description:
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "cos.client")
public class CosClientConfig {

    private String host;

    private String secretId;

    private String secretKey;

    private String region;

    private String bucket;

    @Bean
    public COSClient cosClient() {
        // 初始化用户信息
        COSCredentials credentials = new BasicCOSCredentials(secretId, secretKey);
        // 设置 bucket 地址
        ClientConfig clientConfig = new ClientConfig(new Region(region));
        // 生成 cos 客户端
        return new COSClient(credentials, clientConfig);
    }
}
