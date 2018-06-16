package com.modules.thread;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.modules.thread.utils.GetJsonData;
import com.modules.thread.utils.SqlConnection;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Controller;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Controller
public class ThreadService extends Thread{

    public ThreadService(){
        super();
        start();
    }

    @Override
    public void run() {
        while (true){
            DruidPooledConnection conn = null;
            PreparedStatement statement;
            DruidDataSource dataSource = null;
            ResultSet resultSet;
            try {
                System.out.println("开始线程......");
                List<Map<String, Object>> maps = GetJsonData.getData();
                for (Map<String, Object> map : maps) {
                    try {
                        //开始连接数据库
                        dataSource = SqlConnection.getDataSource(map);
                        conn =dataSource.getConnection();
                    } catch (Exception e) {
                        System.out.println("数据库连接失败");
                        e.printStackTrace();
                    }
                    if (conn != null) {
                        try {
                            System.out.println("现在执行表：" + map.get("tableName"));
                            statement = conn.prepareStatement("select * from t_user");
                            resultSet = statement.executeQuery();

                            while (resultSet.next()){
                                String string = resultSet.getString(1);
                                System.out.println(string);
                            }






                        } catch (SQLException e) {
                            System.out.println("数据库查询错误");
                            e.printStackTrace();
                        }
                    }
                }
                long connectCount = dataSource.getConnectCount();
                long closeCount = dataSource.getCloseCount();
                long dataSourceID = dataSource.getID();
                System.out.println("连接数量：" + connectCount);
                System.out.println("销毁数量：" + closeCount);
                System.out.println("dataSourceID：" + dataSourceID);
                sleep(1000 * 5);



                System.out.println("结束线程......");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    //数据库连接
    public static DruidPooledConnection conn(Map<String, Object> map) throws Exception{
        // String tableName = map.get("tableName").toString();
        String driverClassName = map.get("driverClassName").toString();
        String url = map.get("url").toString();
        String username = map.get("username").toString();
        String password = map.get("password").toString();
        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);


        DruidPooledConnection connection =dataSource.getConnection();
        return connection;
    }

}
