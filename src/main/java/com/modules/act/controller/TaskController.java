package com.modules.act.controller;

import com.modules.act.mapper.MyTask;
import com.modules.leave.service.LeaveService;
import com.utils.DateJsonValueProcessor;
import com.utils.ResponseUtil;
import com.utils.isAjaxRequest;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 代办任务， 历史任务
 * Created by Administrator on 2018/2/26.
 */
@Controller
@RequestMapping("/act/task")
public class TaskController {
    @Resource
    private TaskService taskService;
    @Resource
    private RepositoryService repositoryService;
    @Resource
    private RuntimeService runtimeService;
    @Resource
    private FormService formService;
    @Resource
    private LeaveService leaveService;
    @Resource
    private HistoryService historyService;

    /**
     * 查询产生的历史任务 历史批注
     * @param response
     * @param processInstanceId 流程实例id
     * @return
     * @throws Exception
     */
    @RequestMapping("/listHistoryComment")
    public String listHistoryComment(Model uiModel, HttpServletRequest request, HttpServletResponse response,
                                     String processInstanceId) throws Exception {
        if (isAjaxRequest.isAjaxRequest(request)){
            List<Comment> commentList = taskService
                    .getProcessInstanceComments(processInstanceId);
            // 改变顺序，按原顺序的反向顺序返回list
            Collections.reverse(commentList); //集合元素反转
            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.registerJsonValueProcessor(java.util.Date.class,
                    //时间格式转换
                    new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));
            JSONObject result = new JSONObject();
            JSONArray jsonArray = JSONArray.fromObject(commentList, jsonConfig);
            result.put("code", 0);
            result.put("data", jsonArray);
            ResponseUtil.write(response, result);
        }
        uiModel.addAttribute("processInstanceId", processInstanceId);
        return "modules/act/listHistoryComment";
    }


    /**
     * 根据流程实例processInstanceId查询 或 根据任务taskId查询  任务执行过程
     * @param taskId 任务id
     * @param processInstanceId 流程实例id
     * @param uiModel
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/listAction")
    public String listAction(String taskId, String processInstanceId, Model uiModel,
                             HttpServletRequest request, HttpServletResponse response
                                     ) throws Exception {
        if (isAjaxRequest.isAjaxRequest(request)){
            if (StringUtils.isNotBlank(taskId)){
                HistoricTaskInstance hti = historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
                processInstanceId = hti.getProcessInstanceId(); // 获取流程实例id
            }
            List<HistoricActivityInstance> haiList = historyService.createHistoricActivityInstanceQuery()
                    .processInstanceId(processInstanceId)
                    .orderByHistoricActivityInstanceStartTime()
                    .asc()
                    .list();
            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));
            JSONObject result = new JSONObject();
            JSONArray jsonArray = JSONArray.fromObject(haiList,jsonConfig);
            result.put("code", 0);
            result.put("data", jsonArray);
            ResponseUtil.write(response, result);
        }
        uiModel.addAttribute("taskId", taskId);
        uiModel.addAttribute("processInstanceId", processInstanceId);
        return "modules/act/listAction";
    }

    /**
     * 待办任务
     * @param userName   登录的用户名
     * @param query
     * @param uiModel
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("agencyTask")
    public String agencyTask(@RequestParam(value = "userName", required=false) String userName,
                             @RequestParam(value = "query", required=false, defaultValue = "") String query,
                             @RequestParam(value = "page", required=false, defaultValue = "1") Integer page,  //页码
                             @RequestParam(value = "limit", required=false, defaultValue = "20") Integer limit,  //每页显示数量.
                             Model uiModel, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (isAjaxRequest.isAjaxRequest(request)){
            if (StringUtils.isBlank(userName)){
            /*User user = (User)SecurityUtils.getSubject().getSession().getAttribute("sessionInfo");*/
                userName = (String) SecurityUtils.getSubject().getPrincipal();
            }

            long count = taskService.createTaskQuery().taskAssigneeLikeIgnoreCase(userName)
                    .taskNameLikeIgnoreCase("%" + query + "%").count();

            List<Task> taskList = taskService.createTaskQuery()
                                // 根据用户名查询 不区分大小写
                                .taskAssigneeLikeIgnoreCase(userName)
                                // 根据任务名称模糊查询 不区分大小写
                                .taskNameLikeIgnoreCase("%" + query + "%")
                                //排序
                                .orderByTaskCreateTime().desc()
                                // 返回带分页的结果集合
                                .listPage((page - 1) * limit, limit);
            //这里需要使用一个工具类来转换一下主要是转成JSON格式
            List<MyTask> MyTaskList=new ArrayList<MyTask>();
            for(Task t:taskList){
                MyTask myTask=new MyTask();
                myTask.setId(t.getId());
                myTask.setName(t.getName());
                myTask.setCreateTime(t.getCreateTime());
                myTask.setProcessInstanceId(t.getProcessInstanceId());
                MyTaskList.add(myTask);
            }
            JsonConfig jsonConfig=new JsonConfig();
            jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));
            JSONObject result=new JSONObject();
            JSONArray jsonArray=JSONArray.fromObject(MyTaskList, jsonConfig);
            result.put("code", 0);
            result.put("data", jsonArray);
            result.put("count", count);
            ResponseUtil.write(response, result);
        }
        return "modules/act/agencyTask";
    }

    /**
     * 查询当前流程图
     * @return
     */
    @RequestMapping("/showCurrentView")
    public String showCurrentView(Model uiModel, HttpServletResponse response, String taskId/*, String processDefinitionId*/){
        Boolean isNull = false;
        Task task=taskService.createTaskQuery() // 创建任务查询
                .taskId(taskId) // 根据任务id查询
                .singleResult();
        if(task == null){
            isNull = true;
        }else {
            // 获取流程定义id
            String processDefinitionId = task.getProcessDefinitionId();
            ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery() // 创建流程定义查询
                    // 根据流程定义id查询
                    .processDefinitionId(processDefinitionId)
                    .singleResult();
            // 部署id
            uiModel.addAttribute("deploymentId",processDefinition.getDeploymentId());
            uiModel.addAttribute("diagramResourceName", processDefinition.getDiagramResourceName()); // 图片资源文件名称
            ProcessDefinitionEntity processDefinitionEntity=(ProcessDefinitionEntity)
                    repositoryService.getProcessDefinition(processDefinitionId);
            // 获取流程实例id
            String processInstanceId=task.getProcessInstanceId();
            // 根据流程实例id获取流程实例
            ProcessInstance pi=runtimeService.createProcessInstanceQuery()
                    .processInstanceId(processInstanceId)
                    .singleResult();
            // 根据活动id获取活动实例
            ActivityImpl activityImpl=processDefinitionEntity.findActivity(pi.getActivityId());
            //整理好View视图返回到显示页面
            uiModel.addAttribute("x", activityImpl.getX()); // x坐标
            uiModel.addAttribute("y", activityImpl.getY()); // y坐标
            uiModel.addAttribute("width", activityImpl.getWidth()); // 宽度
            uiModel.addAttribute("height", activityImpl.getHeight()); // 高度
        }
        uiModel.addAttribute("isNull", isNull);
        return "modules/act/showCurrentView";
    }

    /**
     * 查詢流程正常走完的历史流程表
     * @return
     */
    @RequestMapping("/finishedTask")
    public String finishedTask(@RequestParam(value = "userName", required=false) String userName,
                               @RequestParam(value = "query", required=false, defaultValue = "") String query,
                               @RequestParam(value = "page", required=false, defaultValue = "1") Integer page,  //页码
                               @RequestParam(value = "limit", required=false, defaultValue = "20") Integer limit,  //每页显示数量.
                               Model uiModel, HttpServletRequest request, HttpServletResponse response) throws Exception{
        if (isAjaxRequest.isAjaxRequest(request)) {
            if (StringUtils.isBlank(userName)) {
            /*User user = (User)SecurityUtils.getSubject().getSession().getAttribute("sessionInfo");*/
                userName = (String) SecurityUtils.getSubject().getPrincipal();
            }
            List<HistoricTaskInstance> histList = historyService.createHistoricTaskInstanceQuery()
                    .taskAssigneeLikeIgnoreCase(userName)
                    .orderByTaskCreateTime().desc()
                    .taskNameLike("%" + query + "%")
                    .listPage((page - 1) * limit, limit);

            long count = historyService.createHistoricTaskInstanceQuery()
                    .taskAssigneeLikeIgnoreCase(userName)
                    .taskNameLike("%"+ query +"%")
                    .count();
            List<MyTask> taskList=new ArrayList<MyTask>();
            //这里递出没有用的字段，免得给前端页面做成加载压力
            for(HistoricTaskInstance hti:histList){
                MyTask myTask=new MyTask();
                myTask.setId(hti.getId());
                myTask.setName(hti.getName());
                myTask.setCreateTime(hti.getCreateTime());
                myTask.setEndTime(hti.getEndTime());
                myTask.setProcessInstanceId(hti.getProcessInstanceId());
                taskList.add(myTask);
            }
            JsonConfig jsonConfig=new JsonConfig();
            jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));
            JSONObject result=new JSONObject();
            JSONArray jsonArray=JSONArray.fromObject(taskList,jsonConfig);
            result.put("code", 0);
            result.put("data", jsonArray);
            result.put("count", count);
            ResponseUtil.write(response, result);
        }
        return "modules/act/finishedTask";
    }
}
