package org.example.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @ClassName TencentCloudProperties
 * @Version 1.0
 * @Description TencentCloudProperties
 **/
@Component
@Data
@PropertySource("classpath:tencentCloud")
@ConfigurationProperties(prefix = "tencent.cloud")
public class TencentCloudProperties {

    private String secretId;
    private String secretKey;

}
