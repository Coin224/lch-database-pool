package com.lch.dbpool.test;

import com.lch.dbpool.pool.DbPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestMain {

//    private static String driver = "com.mysql.cj.jdbc.Driver";
//    private static String url = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
//    private static String username = "root";
//    private static String password = "root";

    public static void main(String[] args) {
        TestMain db = new TestMain();
        db.save();
//        Connection connection = DbPool.getConn();
//        Class clazz = connection.getClass();
//        String name = clazz.getSimpleName();
//        System.out.println(name);
    }


    public void save() {
        String sql = "insert into firsttable(id,name,password) values(?,?,?)";
        Connection connection = null;
        PreparedStatement state = null;
        try {
            //jdbc六部曲
            //1.导包
            //2.加载驱动
//            Class.forName(driver);
//            //3.获取连接
//            connection = DriverManager.getConnection(url,username,password);
                connection = DbPool.getConn();
            //4.获取状态参数
            state = connection.prepareStatement(sql);
            state.setInt(1,31);
            state.setString(2,"李春宏");
            state.setString(3,"secret");
            //5.执行sql
            int count = state.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //6.关闭连接
            try {
                if (state != null) {
                    state.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
