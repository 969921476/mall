package com.mall.thirdparty.properties;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Author: ChenJunNan
 * @Description:
 * @Date: create in 2021/1/13 10:01
 */
@Component
public class AliuyunProperties implements InitializingBean {
    // 读取配置文件内容
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.keyid}")
    private String keyId;

    @Value("${aliyun.oss.keysecret}")
    private String keySecret;

    @Value("${aliyun.oss.bucketname}")
    private String bucketName;

    // 定义静态常量
    public static String END_POINT;
    public static String KEY_ID;
    public static String KEY_SECRET;
    public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT = endpoint;
        KEY_ID = keyId;
        KEY_SECRET = keySecret;
        BUCKET_NAME = bucketName;
    }
}
