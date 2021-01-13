package com.mall.thirdparty.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author: ChenJunNan
 * @Description:
 * @Date: create in 2021/1/13 10:51
 */
public interface FileUploadService {
    List<String> uploadFile(MultipartFile[] file);

    void deleteFile(String[] urlList);
}
