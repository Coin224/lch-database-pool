package com.lch.dbpool.test;

import com.lch.dbpool.pool.DbPool;
import com.lch.dbpool.pool.MyConn;

import java.sql.Connection;
import java.sql.SQLException;


public class MyThread extends Thread {


    @Override
    public void run() {
        //获取一个连接池对象
        DbPool dbPool = DbPool.getDbPool();
        Connection conn = dbPool.getConn();
        System.out.println(conn);

//        MyConn conn = dbPool.getMyConn();
        try {
            Thread.sleep(6000);
            if (conn != null) {
                conn.close();
            }
            System.out.println("释放了");
        } catch (Exception e) {
            e.printStackTrace();
        }
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
