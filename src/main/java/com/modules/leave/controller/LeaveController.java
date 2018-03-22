package com.modules.leave.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.modules.leave.mapper.Leave;
import com.modules.leave.service.LeaveService;
import com.modules.sys.mapper.User;
import com.utils.*;
import net.sf.json.JSONObject;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.shiro.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/26.
 */
@Controller
@RequestMapping(value = {"leave"})
public class LeaveController {
    @Autowired
    private LeaveService leaveService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "")
    public String leave(@RequestParam(value = "page", required=false, defaultValue = "1") Integer page,  //页码
                        @RequestParam(value = "limit", required=false, defaultValue = "20") Integer limit,  //每页显示数量
                        Model uiModel, HttpServletRequest request, HttpServletResponse response) throws Exception{
        if(isAjaxRequest.isAjaxRequest(request)){
            Leave leave = new Leave();
            PageInfo<Leave> pageList = leaveService.findPageList(leave, page, limit);
            Map<Object, Object> hashMap = Maps.newHashMap();
            hashMap.put("code", 0);
            hashMap.put("data", pageList.getList());
            hashMap.put("count", pageList.getTotal());
            String json = JsonMapper.getInstance().toJson(hashMap);
            ResponseUtil.write(response, json);
            return null;
        }
        return "modules/leave/leave";
    }

    @RequestMapping(value = "addLeave")
    public String addLeave(String leaveDate, Integer leaveDays, String leaveReason,
                           HttpServletRequest request, HttpServletResponse response) throws Exception{
        if(isAjaxRequest.isAjaxRequest(request)){
            Map<Object, Object> hashMap = Maps.newHashMap();
            Leave leave = new Leave();
            Date parseDate = DateUtils.parseDate(leaveDate);
            leave.setLeaveDate(parseDate);
            leave.setLeaveDays(leaveDays);
            leave.setLeaveReason(leaveReason);
            leaveService.insert(leave);
            hashMap.put("code", 0);
            String json = JsonMapper.getInstance().toJson(hashMap);
            ResponseUtil.write(response, json);
            return null;
        }
        return "modules/leave/addLeave";
    }

    //启动流程
    @RequestMapping(value = "startApply")
    @ResponseBody
    public Result startApply(String leaveId, Model uiModel, HttpServletRequest request, HttpServletResponse response){
        try {
            Map<String,Object> variables=new HashMap<String, Object>();
            variables.put("leaveId", leaveId);
            // 启动流程
            ProcessInstance pi= runtimeService.startProcessInstanceByKey("LEAVE_ACTIVITI", variables);
            // 根据流程实例Id查询任务
            Task task=taskService.createTaskQuery().processInstanceId(pi.getProcessInstanceId()).singleResult();
            // 完成 请假单任务
            taskService.complete(task.getId());
            Leave leave = new Leave();
            leave.setId(leaveId);
            leave = leaveService.get(leave);
            //修改状态
            leave.setState("审核中");
            leave.setProcessInstanceId(pi.getProcessInstanceId());
            leaveService.update(leave);
            return Result.successResult().setMsg("申请成功！");
        }catch (Exception e){
            return Result.errorResult().setMsg("申请出错！");
        }
    }

    @RequestMapping("leaveProcess")
    public String leaveProcess(String taskId, String comment, String flag,
                               Model uiModel, HttpServletRequest request, HttpServletResponse response) throws Exception{
        Map<String,Object> variables=new HashMap<String,Object>();
        Task task=taskService.createTaskQuery() // 创建任务查询
                .taskId(taskId) // 根据任务id查询
                .singleResult();
        // 获取流程实例id
        String processInstanceId=task.getProcessInstanceId();
        Leave leave = new Leave();
        leave.setProcessInstanceId(processInstanceId);
        leave = leaveService.get(leave);
       if (isAjaxRequest.isAjaxRequest(request)){
        /*   String leaveId=(String) taskService.getVariable(taskId, "leaveId");
           Leave leave = new Leave();
           leave.setId(leaveId);
           leave = leaveService.get(leave);*/
           //转为小写
           String userName = ((String)SecurityUtils.getSubject().getPrincipal()).toLowerCase();
           User user = (User) SecurityUtils.getSubject().getSession().getAttribute("sessionInfo");
           if(flag.equals("yes") && userName.equals("superadmin")){
               leave.setState("审批通过");
           }else if(flag.equals("yes") && !userName.equals("superadmin")) {
               leave.setState("审核中");
           }else if(flag.equals("no")) {
               leave.setState("审批拒绝");
           }
           variables.put("flag", flag);
           // 设置用户id
           Authentication.setAuthenticatedUserId(user.getUserName() + "[" + user.getName() + "]");
           // 添加批注信息
           taskService.addComment(taskId, processInstanceId, comment);
           // 完成任务
           taskService.complete(taskId, variables);
           leaveService.update(leave);
           JSONObject result=new JSONObject();
           result.put("code", 0);
           result.put("msg", "审批完成！");
           ResponseUtil.write(response, result);
       }
       String leaveDate = DateUtils.formatDate(leave.getLeaveDate(), "yyyy-MM-dd HH:mm:ss");
       uiModel.addAttribute("leaveDate", leaveDate);
       uiModel.addAttribute("leave", leave);
       uiModel.addAttribute("taskId", taskId);
       return "modules/leave/leaveProcess";
    }

    /**
     * 查询流程信息
     * @param response
     * @param taskId  流程实例ID
     * @return
     * @throws Exception
     */
    @RequestMapping("/getLeaveByTaskId")
    public String getLeaveByTaskId(HttpServletResponse response,String taskId) throws Exception{
        //先根据流程ID查询
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
      //  Leave leave = leaveService.getLeaveByTaskId(task.getProcessInstanceId());
        JSONObject result=new JSONObject();
      //  result.put("leave", JSONObject.fromObject(leave));
        ResponseUtil.write(response, result);
        return null;
    }
}
