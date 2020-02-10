package top.snailclimb.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import top.snailclimb.config.AliyunOSSConfig;

import java.io.InputStream;
import java.util.List;

/**
 * @author shuang.kou
 */
@Component
public class AliyunOssUtil implements InitializingBean {

    private final AliyunOSSConfig aliyunOssConfig;
    private static String bucketName;

    public AliyunOssUtil(AliyunOSSConfig aliyunOssConfig) {
        this.aliyunOssConfig = aliyunOssConfig;
    }

    /**
     * 上传文件
     */
    public void upLoadFile(String key, InputStream inputStream) {
        OSS ossClient = aliyunOssConfig.getOssClient();
        // 上传文件
        ossClient.putObject(new PutObjectRequest(bucketName, key, inputStream));
        // 设置权限(公开读)
        ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
    }

    /**
     * 通过文件的唯一标识 key 删除文件
     */
    public void deleteFileByKey(String key) {
        OSS ossClient = aliyunOssConfig.getOssClient();
        // 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
        ossClient.deleteObject(bucketName, key);
        ossClient.shutdown();
    }

    public List<OSSObjectSummary> listAllFiles() {
        return this.listFilesByFileHost("");
    }

    /**
     * 查找特定目录下的所有文件
     */
    public List<OSSObjectSummary> listFilesByFileHost(String fileHost) {
        // 设置最大个数。
        final int maxKeys = 200;
        OSS ossClient = aliyunOssConfig.getOssClient();
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucketName).withPrefix(fileHost).withMaxKeys(maxKeys);
        ObjectListing objectListing = ossClient.listObjects(listObjectsRequest);
        List<OSSObjectSummary> objectSummaries = objectListing.getObjectSummaries();
        ossClient.shutdown();
        return objectSummaries;
    }

    @Override
    public void afterPropertiesSet() {
        bucketName = aliyunOssConfig.getBucketName();
    }

}

