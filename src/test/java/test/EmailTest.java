package test;

import ch.qos.logback.classic.gaffer.PropertyUtil;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.interfaceService.webService.WebServiceDemo;
import com.modules.blogs.mapper.Article;
import com.modules.blogs.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.Date;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: JiangYinCai
 * Date: 2018/3/29
 * Time: 16:41
 * To change this template use File | Settings | File Templates.
 * Description:
 */
/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml",
        "classpath:mybatis-config.xml",
        "classpath:ehcache.xml"})*/
public class EmailTest {
    @Autowired
    private ArticleService articleService;

    @Test
    public void aaa() throws Exception{
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
        String accessKeyId = "LTAI10b9x6obGfBE";
        String accessKeySecret = "cb5S2OjiZDguYG6k3gFKTfCSxJ42p9";

        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);


        boolean bool = ossClient.doesBucketExist("jiangyincai");
        System.out.println(bool);
        String fileName = "123";
        File file = new File("C:\\Users\\Administrator\\Desktop\\123.jpg");
        InputStream inputStream = new FileInputStream(file);

        ObjectMetadata objectMetadata = new ObjectMetadata();
/*        objectMetadata.setContentLength(inputStream.available());
        objectMetadata.setCacheControl("no-cache");
        objectMetadata.setHeader("Pragma", "no-cache");
        objectMetadata.setContentType("jpg");
        objectMetadata.setContentDisposition("inline;filename=" + fileName);*/
        PutObjectResult putResult = ossClient.putObject("jiangyincai", "pic/456.jpg", inputStream, objectMetadata);
        String ret = putResult.getETag();
        System.out.println(ret);
        // 关闭Client。
        ossClient.shutdown();

    }

}
