package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created with IntelliJ IDEA.
 * User: JiangYinCai
 * Date: 2018/3/30
 * Time: 15:35
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class TestUpdate {



    public static void main(String[] args) {
        try {
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/blogs", "root", "123456");
            Statement stat=conn.createStatement();
            StringBuilder  sb=new  StringBuilder();
            for(int  i=900000;i<1000000;i++)
            {
                sb.append("("+(i+1)+",'wowo"+(i+1)+"',"+i+")");
                if(i!=999999)
                {
                    sb.append(",");
                }
            }
           // System.out.println(sb.toString());
            int num=stat.executeUpdate("insert into classfor1(id,name,number) values "+sb.toString());
            System.out.println(num);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
