package com.interfaceService.webListen;

import com.interfaceService.webImpl.WebServiceDemoImpl;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;
import javax.xml.ws.Endpoint;

/**
 * Created with IntelliJ IDEA.
 * User: JiangYinCai
 * Date: 2018/4/21
 * Time: 16:21
 * To change this template use File | Settings | File Templates.
 * Description:
 */
//发布接口
public class WebListen extends ContextLoaderListener{

    public void contextInitialized(ServletContextEvent event) {
        String address="http://localhost:8081/webServiceDemo";
        Endpoint.publish(address, new WebServiceDemoImpl());
        super.contextInitialized(event);
    }
}
