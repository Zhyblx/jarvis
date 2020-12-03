package org.jarvis.operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class InsertData {

    // 通过完整的sql插入数据库
    public void setInsertData(String strSql) {
        try {

            Class.forName("org.sqlite.JDBC");
            // 连接到数据库Dxy.db
            Connection connection = DriverManager.getConnection("jdbc:sqlite:BlackCard.db");
            Statement statement = connection.createStatement();
            String sql = strSql;
            statement.executeUpdate(sql);
            statement.close();
            connection.close();

            System.out.println(sql);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
