package com.interfaceService.webImpl;

import com.alibaba.fastjson.JSONObject;
import com.interfaceService.webService.WebServiceDemo;

import javax.jws.WebService;
import java.util.Date;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: JiangYinCai
 * Date: 2018/4/21
 * Time: 16:17
 * To change this template use File | Settings | File Templates.
 * Description:
 */
//实现接口
@WebService
public class WebServiceDemoImpl implements WebServiceDemo {

    @Override
    public String query(String id) {
        String xml = "query";
        System.out.println(xml);

        System.out.println("id的值为： " + id);
        return "<?xml version='1.0' encoding='UTF-8'?><data><item><key>idNumber</key><value type=\"string\">36252319921130161X</value></item><item><key>name</key><value type=\"string\">aaaa</value></item><item><key>nation</key><value type=\"string\">01</value></item></data>";
    }

    @Override
    public String query1(String id) {
        String xml = "query1";
        System.out.println(xml);
        System.out.println("id的值为： " + id);
        return "<?xml version='1.0' encoding='UTF-8'?><data><item><key>idNumber</key><value type=\"string\">36252319921130161X</value></item><item><key>name</key><value type=\"string\">aaaa</value></item><item><key>nation</key><value type=\"string\">01</value></item></data>";
    }
}

