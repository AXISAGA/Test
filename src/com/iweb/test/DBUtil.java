package com.iweb.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** jdbc工具类 负责将驱动加载和连接创建的代码进行封装复用
 * @author Guan
 * @date 2023/6/1 9:15
 */
public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/gls?characterEncoding=utf8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "a12345";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,USERNAME,PASSWORD);
    }

}
