package com.modules.thread.utils;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.alibaba.druid.util.DruidDataSourceUtils;
import com.alibaba.fastjson.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SqlConnection {

    //数据库连接
    public static DruidDataSource getDataSource(Map<String, Object> map) throws Exception{
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
        dataSource.setTimeBetweenEvictionRunsMillis(1000 * 1);
        return dataSource;
    }


    public static void main(String[] args) {


        Map hashMap = new HashMap<String, String>();
        DruidPooledConnection conn = null;
        DruidDataSource dataSource = null;
        hashMap.put("driverClassName", "com.mysql.jdbc.Driver");
        hashMap.put("url", "jdbc:mysql://localhost:3306/blogs?useUnicode=true&characterEncoding=UTF-8");
        hashMap.put("username", "root");
        hashMap.put("password", "123456");


        try {
            dataSource = getDataSource(hashMap);
            conn = dataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement("select * from t_user");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                String string = resultSet.getString(1);
                System.out.println(string);
            }



        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(conn != null ){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
