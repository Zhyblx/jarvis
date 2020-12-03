package org.jarvis.operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * 类：CreateDataBase
 * 作用：创建数据库
 */

public class CreateDataBase {

    private Connection connection = null;
    private Statement statement = null;

    public void setCreateDataBase(String sql) throws Exception {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:BlackCard.db");
        statement = connection.createStatement();
//            String sql = "CREATE TABLE AddressInfo(type varchar(255),address1 varchar(255),address2 varchar(512),address3 varchar(512),address4 varchar(512));"; //京东地址
//            String sql = "CREATE TABLE category(catId1 varchar(255),catId2 varchar(255),catId3 varchar(512),catId4 varchar(512),catId5 varchar(512));"; //类目

        statement.executeUpdate(sql);
        statement.close();
        connection.close();
        System.out.println("数据库创建成功!");


    }

}
