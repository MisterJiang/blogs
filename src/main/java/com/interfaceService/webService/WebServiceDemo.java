package com.interfaceService.webService;

import com.alibaba.fastjson.JSONObject;

import javax.jws.WebService;
import javax.ws.rs.*;

/**
 * Created with IntelliJ IDEA.
 * User: JiangYinCai
 * Date: 2018/4/21
 * Time: 16:16
 * To change this template use File | Settings | File Templates.
 * Description:
 */
//写接口
@Produces({"text/xml"})
public interface WebServiceDemo {

    @GET
    @Path("/query_post/{id}")
    String query(@PathParam("id") String id);

    @POST
    @Path("/query1_post")
    String query1(String id);

}
