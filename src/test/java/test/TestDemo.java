package test;

import com.github.pagehelper.PageInfo;
import com.modules.blogs.mapper.Article;
import com.modules.blogs.service.ArticleService;
import com.modules.sys.mapper.User;
import com.modules.sys.service.UserService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Task;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Test;

import java.util.List;

/**
 * Created by Administrator on 2018/2/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml",
        "classpath:mybatis-config.xml",
        "classpath:ehcache.xml"})
public class TestDemo {



    private static Logger logger = LoggerFactory.getLogger(Test.class);


//ceshis

    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;

    @Test
    public void ai(){
        /*List<Task> taskList=taskService.createTaskQuery().taskAssignee("admin").list();
                // 根据用户id查询
               // .taskCandidateGroup("admin").list();

       for (Task task: taskList){
           System.out.println(task.toString());
       }*/
        /*HistoricTaskInstance hti=historyService.createHistoricTaskInstanceQuery().taskId("15").singleResult();
        String processInstanceId=hti.getProcessInstanceId(); // 获取流程实例id
        List<HistoricActivityInstance> haiList=historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).list();
        for(HistoricActivityInstance historicActivityInstance : haiList){
            System.out.println(historicActivityInstance.getId());
            System.out.println(historicActivityInstance.getActivityName());
            System.out.println(historicActivityInstance.getAssignee());
            System.out.println(historicActivityInstance.getStartTime());
            System.out.println(historicActivityInstance.getEndTime());
            System.out.println(historicActivityInstance.getDurationInMillis());
            System.out.println("**********************************");
        }*/

       // System.out.println("");
        User user = new User();
        PageInfo<User> pageList = userService.findPageList(user,1 , 20);
        System.out.println(pageList.getList());
    }


}
