package com.lch.dbpool.pool;

import com.lch.dbpool.exception.NoConnExeception;
import com.lch.dbpool.reader.ConfigReader;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * 这是一个连接池
 * 为了项目启动的时候就创建多个连接
 * 这样就能同时使用多个连接
 * 提升效率
 */
public class DbPool {



//    //这个属性是已使用连接数
//    private static int count = 0;

    /*连接池中连接的最少数量*/
    private static int MinConnNum = Integer.parseInt(ConfigReader.getValue("MinConnNum"));
    private static int maxWaitTime = Integer.parseInt(ConfigReader.getValue("maxWaitTime"));




    /*需要一个集合来存放这些连接*/
    private static List<MyConn> conns = new ArrayList<>();

    /*连接池一般一个系统一个，所以做成单例模式*/
    // 私有的构造方法
    private DbPool() {}

    // 私有的属性
    // 防止指令重排序
    private volatile static DbPool dbPool;

    // 返回连接池对象的方法
    public static DbPool getDbPool() {
        if (dbPool == null) {
            //在这里加锁  防止多个人同时访问连接池产生的线程安全问题
            //这里dbpool已经为 null 我们排队进入锁里面 第一个人进去了 创建了对象 出来 第二个人进去了 出来也又创建了对象
            //线程非安全 所以在里面再判断一下
            synchronized (DbPool.class) {
                if (dbPool == null) {//double check  这次判断为空时真的为空了
                    dbPool = new DbPool();
                }
            }
        }
        return dbPool;
    }


    /*在启动项目的时候就创建多个连接*/
    static {
        //做一个循环 每一次都创建一个MyConn对象
        for (int i = 0 ; i < MinConnNum ; i++) {
            MyConn conn = new MyConn();
            //把MyConn对象添加到集合中
            conns.add(conn);
        }
    }

    /**
     * 因为多人同时访问的时候
     * 可能几个人会拿到同一个连接
     * 线程不安全
     * 所以加一把锁
     */
    //这个方法从集合中得到MyConn对象
    private synchronized MyConn getMyConn(){
        MyConn result = null;
        // 从连接池中获取一个可用的连接
        for (int i = 0 ; i < conns.size() ; i++) {
            MyConn conn = conns.get(i);
            if (!conn.getUsed()) {
                conn.setUsed(true);
                result = conn;
                //找到就退出
                break;
            }
        }
        return result;
    }


    // 加一个等待机制
    public Connection getConn() {
        MyConn conn = this.getMyConn();
        int count = 0;//计算秒数
        while (conn == null && count < maxWaitTime*10) {
            try {
                // 再获取连接
                conn = this.getMyConn();
                // 如果conn 为空 等待0.1秒 count++
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
        }
        // 等待5秒之后，如果依然为空  抛出异常
        if (conn == null) {
            throw new NoConnExeception("等待超时");
        }
        return conn;
    }


    //可以手动添加连接池数量
    public static void add(int num) {
        for (int i = 0 ; i < num ; i++) {
            conns.add(new MyConn());
        }
    }
}
