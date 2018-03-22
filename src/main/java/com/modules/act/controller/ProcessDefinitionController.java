package com.modules.act.controller;

import com.utils.DateJsonValueProcessor;
import com.utils.ResponseUtil;
import com.utils.isAjaxRequest;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * 流程定义管理
 * Created by Administrator on 2018/2/27.
 */
@Controller
@RequestMapping("act/processDefinition")
public class ProcessDefinitionController {

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private HistoryService historyService;


    //流程部署列表查询
    @RequestMapping(value = "processDefinition")
    public String processDefinition(@RequestParam(value = "query", required=false, defaultValue = "") String query,
                           @RequestParam(value = "page", required=false, defaultValue = "1") Integer page,  //页码
                           @RequestParam(value = "limit", required=false, defaultValue = "20") Integer limit,  //每页显示数量.
                           HttpServletRequest request, HttpServletResponse response) throws Exception{
        if (isAjaxRequest.isAjaxRequest(request)){
            long count=repositoryService.createProcessDefinitionQuery()
                    .processDefinitionNameLike("%"+query+"%")
                    .count();
            List<ProcessDefinition> processDefinitionList=repositoryService.createProcessDefinitionQuery()
                    .orderByDeploymentId().desc()
                    .processDefinitionNameLike("%"+query+"%")
                    .listPage((page-1)*limit, limit);
            JsonConfig jsonConfig=new JsonConfig();
            jsonConfig.setExcludes(new String[]{"identityLinks", "processDefinition"});
            jsonConfig.registerJsonValueProcessor(java.util.Date.class,
                    new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));
            JSONObject result=new JSONObject();
            JSONArray jsonArray=JSONArray.fromObject(processDefinitionList,jsonConfig);
            result.put("code", 0);
            result.put("data", jsonArray);
            result.put("count", count);
            ResponseUtil.write(response, result);
        }
        return "modules/act/processDefinition";
    }


    /**
     * 查看流程图
     * @param deploymentId  流程ID
     * @param diagramResourceName  图片名称
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/showView")
    public String showView(String deploymentId,String diagramResourceName,HttpServletResponse response)throws Exception{
        InputStream inputStream=repositoryService.getResourceAsStream(deploymentId, diagramResourceName);
        OutputStream out=response.getOutputStream();
        for(int b=-1;(b=inputStream.read())!=-1;){
            out.write(b);
        }
        out.close();
        inputStream.close();
        return null;
    }
}
