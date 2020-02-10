package top.snailclimb.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * @author shuang.kou
 */
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
@Getter
@Setter
@ToString
public class AliyunOSSConfig {
    private String bucketName;
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    /**
     * 存储路径
     */
    private String fileHost;

    public OSS getOssClient() {
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }
}
