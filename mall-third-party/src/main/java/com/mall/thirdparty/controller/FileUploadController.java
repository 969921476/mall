package com.mall.thirdparty.controller;

import com.mall.common.utils.R;
import com.mall.thirdparty.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author: ChenJunNan
 * @Description:
 * @Date: create in 2021/1/13 14:31
 */
@RestController
@RequestMapping("/thirdparty")
public class FileUploadController {
    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping("/uploadFile")
    public R uploadFile(@RequestParam("file") MultipartFile[] file) {
        List<String> urlList = fileUploadService.uploadFile(file);
        return R.ok().put("data", urlList);
    }

    @DeleteMapping("/deleteFile")
    public R deleteFile(@RequestBody String[] urlList) {
        fileUploadService.deleteFile(urlList);
        return R.ok();
    }
}
