package com.modules.act.controller;

import com.utils.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import com.google.common.collect.Maps;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

/**
 * 工作流-流程部署
 * Created by Administrator on 2018/2/24.
 */
@Controller
@RequestMapping(value = {"act"})
public class DeployController {

    //注入activitiService服务
    //repositoryService的主要作用是管理流程仓库，例如部署，删除，读取流程资源等
    @Autowired
    private RepositoryService repositoryService;

    //列表初始化
    @RequestMapping(value = "deploy")
    public String deployList(HttpServletRequest request, HttpServletResponse response) throws Exception{
        if(isAjaxRequest.isAjaxRequest(request)){
            ResponseUtil.write(response, "");
        }
        return "modules/act/deployList";
    }


    //流程部署列表查询
    @RequestMapping(value = "deployList")
    @ResponseBody
    public String listPage(@RequestParam(value = "query", required=false, defaultValue = "") String query,
                       @RequestParam(value = "page", required=false, defaultValue = "1") Integer page,  //页码
                       @RequestParam(value = "limit", required=false, defaultValue = "20") Integer limit,  //每页显示数量.
                       HttpServletRequest request, HttpServletResponse response) throws Exception{
        //取得总数量
        long count=repositoryService.createDeploymentQuery().deploymentNameLike("%"+query+"%").count();
        //当前页要显示的list
        List<Deployment> listPage = repositoryService.createDeploymentQuery()
                .orderByDeploymenTime().asc()
                .deploymentNameLike("%" + query + "%")
                .listPage((page-1)*limit, limit);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[]{"resources"});
        jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));
        JSONObject result=new JSONObject();
        JSONArray jsonArray=JSONArray.fromObject(listPage, jsonConfig);
        result.put("code", 0);
        result.put("data", jsonArray);
        result.put("count", count);
        ResponseUtil.write(response, result);
        return null;
    }


    //流程部署
    @RequestMapping(value = "addDeploy")
    @ResponseBody
    @RequiresRoles(value={"superadmin"}, logical= Logical.AND)  //超级管理员角色
    public String addDeploy(String name, HttpServletRequest request, HttpServletResponse response){
        Map<Object, Object> hashMap = Maps.newHashMap();
        /*if(StringUtils.isBlank(name)){
            hashMap.put("code", 2);
            hashMap.put("msg", "部署名称不能为空！");
        }else {*/
            try {
                //一次只有一个zip文件，没有循环读取多个
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                Iterator<String> iter = multiRequest.getFileNames();
                MultipartFile deployFile = multiRequest.getFile(iter.next());
                String originalFilename = deployFile.getOriginalFilename(); //文件名称
                //部署
                repositoryService.createDeployment() //创建部署
                        .name(originalFilename.substring(0, originalFilename.lastIndexOf(".")))	//需要部署流程名称
                        .addZipInputStream(new ZipInputStream(deployFile.getInputStream()))//添加ZIP输入流
                        .deploy();//开始部署
                hashMap.put("code", 0);
                hashMap.put("msg", "部署成功！");
            }catch (Exception e){
                hashMap.put("code", 1);
                hashMap.put("msg", "部署失败，请联系管理员！");
            }
       // }
        String json = JsonMapper.getInstance().toJson(hashMap);
        return json;
    }

    //删除流程
    @RequestMapping(value = "deleteDeploy")
    @ResponseBody
    @RequiresRoles(value={"superadmin"}, logical= Logical.AND)  //超级管理员角色
    public Result deleteDeploy(String id, HttpServletRequest request, HttpServletResponse response){
        repositoryService.deleteDeployment(id, true);
        return Result.successResult().setMsg("删除成功！");
    }
}
