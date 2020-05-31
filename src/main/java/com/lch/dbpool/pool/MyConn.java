package com.lch.dbpool.pool;

import com.lch.dbpool.adapter.ConnAdapter;
import com.lch.dbpool.reader.ConfigReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 这是一个自己定义的类
 * 为了给数据库连接绑定一个状态
 * 让它close的时候并不是真的close
 * 而是把状态改变
 */
public class MyConn extends ConnAdapter {

    private Connection conn;
    private Boolean used = false;//默认是空闲的


    private static String driver = ConfigReader.getValue("driver");
    private static String url = ConfigReader.getValue("url");
    private static String username = ConfigReader.getValue("username");
    private static String password = ConfigReader.getValue("password");


    static {
        try {
            // 加载驱动
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    //每创建一个对象就创建一个连接
    {
        //获取连接
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    //这个方法是用来改变MyConn的状态
    @Override
    public void close() throws SQLException {
        this.setUsed(false);
    }


    /**
     * 这个方法判断MyConn的状态 false为空闲 true为繁忙
     */
    @Override
    public boolean isClosed() {
        return this.getUsed();
    }


    /**这个方法有用conn属性来调用
     * conn的值在连接池中赋值
     */
    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }
}
