package com.mall.thirdparty.service.Impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.mall.thirdparty.properties.AliuyunProperties;
import com.mall.thirdparty.service.FileUploadService;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author: ChenJunNan
 * @Description:
 * @Date: create in 2021/1/13 10:52
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Override
    public List<String> uploadFile(MultipartFile[] file) {
        List<String> list = new ArrayList<>();
        if (file.length > 1) {
            for (MultipartFile f : file) {
                String url = upload(f);
                list.add(url);
            }
            return list;
        }
        if (file.length == 1) {
            String url = upload(file[0]);
            list.add(url);
        }
        return list;
    }

    @Override
    public void deleteFile(String[] urlList) {
        if (urlList.length > 1) {
            for (String url: urlList) {
                delete(url);
            }
        }
        if (urlList.length == 1) {
            delete(urlList[0]);
        }
    }

    private String upload(MultipartFile file) {
        // 获取地域节点。
        String endpoint = AliuyunProperties.END_POINT;
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = AliuyunProperties.KEY_ID;
        String accessKeySecret = AliuyunProperties.KEY_SECRET;
        // 获取名称
        String bucketName = AliuyunProperties.BUCKET_NAME;
        // 上传文件流。
        InputStream inputStream = null;
        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            inputStream = file.getInputStream();
            // 获取文件名称，并且获取后缀名
            String fileName = FilenameUtils.getExtension(file.getOriginalFilename());
            // 添加唯一值 放止名称相同
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            fileName = uuid + "." + fileName;
            // 把文件按照日期进行分类
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String datePath = LocalDateTime.now().format(dateTimeFormatter);
            fileName = datePath + "/" + fileName;
            ossClient.putObject(bucketName, fileName, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
            // 拼接阿里云返回的路径
            String url = "https://" + bucketName + "." + endpoint + "/" + fileName;
            return url;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void delete(String url) {
        // 获取地域节点。
        String endpoint = AliuyunProperties.END_POINT;
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = AliuyunProperties.KEY_ID;
        String accessKeySecret = AliuyunProperties.KEY_SECRET;
        // 获取名称
        String bucketName = AliuyunProperties.BUCKET_NAME;

        // 对url进行处理
        String[] split = url.split(endpoint+"/");
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 删除文件。
        ossClient.deleteObject(bucketName, split[1]);
        // 关闭OSSClient。
        ossClient.shutdown();
    }
}
