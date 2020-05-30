package com.lch.dbpool.pool;

import com.lch.dbpool.reader.ConfigReader;
import com.mysql.cj.jdbc.ConnectionImpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 这是一个自己定义的类
 * 为了给数据库连接绑定一个状态
 * 让它close的时候并不是真的close
 * 而是把状态改变
 */
public class MyConn extends ConnectionImpl {

    private Connection conn;
    private Boolean isBusy = false;//默认是空闲的

//    private static String url;
//    private static String username;
//    private static String password;

    static {
        try {
            // 加载驱动
            Class.forName(ConfigReader.getValue("driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
            // 加载类
//            Properties prop = new Properties();
//            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties"));
//            String driver = (String) prop.get("driver");
//            url = (String) prop.get("url");
//            username = (String) prop.get("username");
//            password = (String) prop.get("password");
            // 加载驱动
    }


    //每创建一个对象就创建一个连接
    {
        //获取连接
        try {
            conn = DriverManager.getConnection(ConfigReader.getValue("url"),
                    ConfigReader.getValue("username"), ConfigReader.getValue("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConn() {
        return conn;
    }

    // 获取状态
    public Boolean getBusy() {
        return isBusy;
    }

    // 设置状态
    public void setBusy(Boolean busy) {
        isBusy = busy;
    }


    //这个方法是用来改变MyConn的状态
    @Override
    public void close() throws SQLException {
        //获取到已连接的数量
        int count = DbPool.getCount();
        //关闭的时候就--
        count--;
        //重新给count赋值
        DbPool.setCount(count);
        this.setBusy(false);
    }


    /**
     * 这个方法判断MyConn的状态 false为空闲 true为繁忙
     */
    @Override
    public boolean isClosed() {
        return this.getBusy();
    }


    /**这个方法有用conn属性来调用
     * conn的值在连接池中赋值
     */
    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }
}
