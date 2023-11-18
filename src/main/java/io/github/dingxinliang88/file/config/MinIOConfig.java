package io.github.dingxinliang88.file.config;

import io.github.dingxinliang88.file.service.FileStorageService;
import io.minio.MinioClient;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * minio 配置
 *
 * @author codejuzi
 */
@Data
@Configuration
@EnableConfigurationProperties({MinIOConfigProperties.class})
@ConditionalOnClass({FileStorageService.class})
public class MinIOConfig {

    @Resource
    private MinIOConfigProperties minIOConfigProperties;

    /**
     * 构建minio客户端
     *
     * @return minio client
     */
    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .credentials(minIOConfigProperties.getAccessKey(), minIOConfigProperties.getSecretKey())
                .endpoint(minIOConfigProperties.getEndPoint())
                .build();
    }
}
