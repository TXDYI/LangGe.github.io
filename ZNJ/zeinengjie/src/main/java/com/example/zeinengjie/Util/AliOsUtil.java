package com.example.zeinengjie.Util;

import com.aliyun.oss.*;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class AliOsUtil {
    @Resource
    private OssConfig ossConfig; // 注入 OSS 配置信息

    public void uploadFile(MultipartFile file, String objectName) {
        // 1. 创建 OSS 客户端
        OSS ossClient = new OSSClientBuilder().build(
                ossConfig.getEndpoint(),
                ossConfig.getAccessKeyId(),
                ossConfig.getAccessKeySecret()
        );
        try {
            // 2. 获取文件流
            InputStream inputStream = file.getInputStream();

            // 3. 创建上传请求（注意这里用的是 inputStream，而不是 File）
            PutObjectRequest putObjectRequest = new PutObjectRequest(
                    ossConfig.getBucketName(), objectName, inputStream
            );
            // 4. 上传文件
            ossClient.putObject(putObjectRequest);
            System.out.println("✅ 文件上传成功：" + objectName);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 5. 关闭 OSS 客户端
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
    public void removeFile(String objectName){
        String[] parts = objectName.split("/");
        String imgUsr="";
        // 拼接第三个 '/' 之后的部分
        if (parts.length > 3) {
            StringBuilder result = new StringBuilder();
            for (int i = 3; i < parts.length; i++) {
                result.append(parts[i]);
                if (i < parts.length - 1) {
                    result.append("/"); // 添加斜杠
                }
            }
            imgUsr = result.toString();
        } else {
            System.out.println("URL 格式不正确");
        }
        System.out.println(imgUsr);
        // 1. 创建 OSS 客户端
        OSS ossClient = new OSSClientBuilder().build(
                ossConfig.getEndpoint(),
                ossConfig.getAccessKeyId(),
                ossConfig.getAccessKeySecret()
        );
            try {
                // 2. 删除文件
                ossClient.deleteObject(ossConfig.getBucketName(), imgUsr);

                // 3. 关闭 OSS 客户端
                ossClient.shutdown();

                System.out.println("✅ 文件删除成功：" + objectName);
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                // 5. 关闭 OSS 客户端
                if (ossClient != null) {
                    ossClient.shutdown();
                }
            }
    }
    public String createImgFileName(){
        UUID uuid = UUID.randomUUID();
        String objectName = "uploads/" + System.currentTimeMillis() + "_" + uuid.toString()+".png";
        return objectName;
    }
}
