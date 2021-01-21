package com.mall.thirdparty.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @Author: ChenJunNan
 * @Description:
 * @Date: create in 2021/1/13 10:51
 */
public interface FileUploadService {
    List<String> uploadFile(MultipartFile[] file);

    void deleteFile(String[] urlList);

    Map<String, String> policy();

}
