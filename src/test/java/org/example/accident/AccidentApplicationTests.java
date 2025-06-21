package org.example.accident;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.*;

@SpringBootTest
class AccidentApplicationTests {

    @Test
    void contextLoads() {
        String dbURL = "jdbc:sqlserver://localhost;databaseName=accident;user=uk;password=mjj123321;encrypt=true;trustServerCertificate=true";
        try {
            // 加载SQL Server驱动程序类
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("加载驱动成功！");
            //2.连接
            Connection dbConn = DriverManager.getConnection(dbURL);//这里的密码改为第一步你所修改的密码，用户名一般就为"sa"
            System.out.println("连接数据库成功！");
            String sql="select * from Accident";//这个语句就是表的查询语句，按照你所建的表修改名称
            PreparedStatement statement=null;
            statement= dbConn.prepareStatement(sql);
            ResultSet res=null;
            res=statement.executeQuery();
            while(res.next()){
                int AccidentID = res.getInt("AccidentID"); // 假设 AccidentID 是整数类型
                Date OccurrenceTime = Date.valueOf(res.getString("OccurrenceTime"));
                String OccurrenceLocation = res.getString("OccurrenceLocation");
                String AccidentType = res.getString("AccidentType");
                String AccidentLevel = res.getString("AccidentLevel");

                System.out.println("AccidentID: " + AccidentID + ", OccurrenceTime : " + OccurrenceTime+", OccurrenceLocation: " + OccurrenceLocation+", AccidentType: " + AccidentType+", AccidentLevel: " + AccidentLevel );

            }
        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("连接数据库失败！");
        }
    }

}
