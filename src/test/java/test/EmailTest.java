package test;

import ch.qos.logback.classic.gaffer.PropertyUtil;
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
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml",
        "classpath:mybatis-config.xml",
        "classpath:ehcache.xml"})
public class EmailTest {
    @Autowired
    private ArticleService articleService;

    @Test
    public void aaa(){


    }

}
