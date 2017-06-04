package com.guoan.guoan_interface_15.alioss;

import com.aliyun.openservices.oss.OSSClient;
import com.aliyun.openservices.oss.model.ObjectMetadata;
import com.guoan.utils.AliyunOSSUtils;
import com.guoan.utils.DateUtil;
import org.apache.poi.util.SystemOutLogger;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhaotongbeyond@qq.com on 2015/6/4.
 */
public class TestOss {

    private static final String ACCESS_ID = "Nw6mJXzklWOfJfAH";
    private static final String ACCESS_KEY = "aAPCjVoPFDF915DgsmCh89lwuE3Pwk";
    private static final String OSS_ENDPOINT = "http://oss-cn-beijing.aliyuncs.com";

    @Test
    public void testCreateFoler() throws IOException {

        //要创建的文件夹名称,在满足Object命名规则的情况下以"/"结尾
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");

        String formatDate = simpleDateFormat.format(date);
        System.out.println(formatDate);
        String objectName = formatDate+"/";
        OSSClient client = new OSSClient(OSS_ENDPOINT, ACCESS_ID, ACCESS_KEY);
        ObjectMetadata objectMeta = new ObjectMetadata();
        /*这里的size为0,注意OSS本身没有文件夹的概念,这里创建的文件夹本质上是一个size为0的Object,dataStream仍然可以有数据
         *照样可以上传下载,只是控制台会对以"/"结尾的Object以文件夹的方式展示,用户可以利用这种方式来实现
         *文件夹模拟功能,创建形式上的文件夹
         */
        byte[] buffer = new byte[0];
        ByteArrayInputStream in = new ByteArrayInputStream(buffer);
        objectMeta.setContentLength(0);
        try {
            client.putObject(AliyunOSSUtils.BUCKET_NAME, objectName, in, objectMeta);
        } finally {
            in.close();
        }
    }
}
