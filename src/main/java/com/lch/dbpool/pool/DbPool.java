package com.lch.dbpool.pool;

import com.lch.dbpool.exception.NoConnExeception;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 这是一个连接池
 * 为了项目启动的时候就创建多个连接
 * 这样就能同时使用多个连接
 * 提升效率
 */
public class DbPool {



    //这个属性是已使用连接数
    private static int count = 0;

    /*最大连接数*/
    private static int MaxConnNum = 10;

    /*需要一个集合来存放这些连接*/
    private static List<MyConn> conns = new ArrayList<>();

    /*连接池一般一个系统一个，所以做成单例模式*/
    // 私有的构造方法
    private DbPool() {}

    // 私有的属性
    private static DbPool dbPool;

    // 返回连接池对象的方法
    public static DbPool getDbPool() {
        if (dbPool == null) {
            return new DbPool();
        }
        return dbPool;
    }
    /*在启动项目的时候就创建多个连接*/
    static {
        //做一个循环 每一次都创建一个MyConn对象
        for (int i = 0 ; i < MaxConnNum ; i++) {
            MyConn conn = new MyConn();
            //把MyConn对象添加到集合中
            conns.add(conn);
        }
    }

    //这个方法从集合中得到MyConn对象
    public static Connection getConn(){
        if (count >8) throw new NoConnExeception("连接数不够用啦");
        //获取一个count count++
        count++;
        MyConn conn = conns.get(count);
        //设置MyConn的状态为繁忙
        conn.setBusy(true);
        //把Connection对象返回
        return conn;
    }


    //可以手动添加连接池数量
    public static void add(int num) {
        for (int i = 0 ; i < num ; i++) {
            conns.add(new MyConn());
        }
    }


    public static void release() {
        // 遍历连接池 找到空闲的 关闭
        for (MyConn conn : conns) {
            if (conn.getBusy()) {
                try {
                    conn.getConn().close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //这个方法是获取连接的数量
    public static int getCount() {
        return count;
    }

    //这个方法用来给MyConn调用close方法的时候改变状态时
    //count--
    public static void setCount(int count) {
        DbPool.count = count;
    }
}
