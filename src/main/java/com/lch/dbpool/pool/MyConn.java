package com.lch.dbpool.pool;

import com.mysql.cj.jdbc.ConnectionImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 这是一个自己定义的类
 * 为了给数据库连接绑定一个状态
 * 让它close的时候并不是真的close
 * 而是把状态改变
 */
public class MyConn extends ConnectionImpl {

    public Connection conn;
    public Boolean isBusy = false;

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Boolean getBusy() {
        return isBusy;
    }

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


    //这个方法判断MyConn的状态 false为空闲 true为繁忙
    @Override
    public boolean isClosed() {
        return this.getBusy();
    }


    //这个方法有用conn属性来调用
    //conn的值在连接池中赋值
    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }
}
